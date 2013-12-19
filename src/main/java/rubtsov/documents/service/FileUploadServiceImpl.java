package rubtsov.documents.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rubtsov.documents.utils.Preferences;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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
