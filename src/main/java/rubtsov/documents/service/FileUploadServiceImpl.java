package rubtsov.documents.service;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rubtsov.documents.utils.Preferences;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.READ;

/**
 * Created by mrubtsov on 19.12.13.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    Preferences preferences;

    @Override
    public boolean saveDocumentImage(Long docId, MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        try {
            writeFile(file.getInputStream(), Files.newOutputStream(createFile(docId, extension)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public byte[] getDocumentImage(Long docId) {
        String folderPath = preferences.getImagesFolder() + docId;

        String filePath = folderPath + File.separator + "image.jpg";

        Path file = Paths.get(filePath);

        if (!Files.exists(file)) {
            return null;
        }

        try {
            return IOUtils.toByteArray(Files.newInputStream(file, READ));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] getDocumentScaledImage(Long docId) {
        String folderPath = preferences.getImagesFolder() + docId;

        String filePath = folderPath + File.separator + "image.jpg";

        Path file = Paths.get(filePath);

        if (!Files.exists(file)) {
            return null;
        }

        try {
            InputStream in = Files.newInputStream(file, READ);
            BufferedImage origImg = ImageIO.read(in);

            if (getMaxImageFactor(origImg) > preferences.getMaxImageFactor()) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(resizeImage(origImg), "jpg", os);
                return os.toByteArray();
            } else {
                return IOUtils.toByteArray(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getMaxImageFactor(BufferedImage origImg) {
        return Math.max(origImg.getHeight(), origImg.getWidth());
    }

    private BufferedImage resizeImage(BufferedImage origImg) {
        int origW = origImg.getWidth();
        int origH = origImg.getHeight();

        int scaledW, scaledH;

        if (origW > origH) {
            scaledW = preferences.getMaxImageFactor();
            scaledH = origH * scaledW / origW;
        } else {
            scaledH = preferences.getMaxImageFactor();
            scaledW = origW * scaledH / origH;
        }

        BufferedImage scaledImg = null;
        if(origImg != null) {
            scaledImg = new BufferedImage(scaledW, scaledH, origImg.getType());
            Graphics2D g = scaledImg.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance((double) scaledW / origW, (double) scaledH / origH);
            g.drawRenderedImage(origImg, at);
        }

        return scaledImg;
    }

    private Path createFile(Long docId, String extension) throws IOException {
        String folderPath = preferences.getImagesFolder() + docId;

        String filePath = folderPath +
                File.separator + "image." + extension;

        Path file = Paths.get(filePath);

        Path folder = Paths.get(folderPath);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        } else {
            if (Files.exists(file)) {
                String targetPath = folderPath + File.separator + new Date().getTime() + '.' + extension;
                Files.move(file, Paths.get(targetPath), REPLACE_EXISTING, ATOMIC_MOVE);
            }
        }

        return Files.createFile(file);
    }

    private void writeFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        int read;
        byte[] bytes = new byte[1024];

        while ((read = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, read);
        }
        bufferedOutputStream.flush();
    }


}
