package rubtsov.documents.web.controller;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.dto.FileDto;
import rubtsov.documents.service.DocumentsService;
import rubtsov.documents.web.Utils.Views;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardCopyOption.*;

/**
 * Created by mike on 11.12.13.
 */
@Controller
@RequestMapping(Views.DOCUMENTS + "/**")
public class DocumentsController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(DocumentsController.class);

    @Autowired
    DocumentsService documentsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDocs(ModelMap model) {

        model.put("docs", documentsService.getAllDocsDtos());

        return Views.DOCUMENTS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.DOCUMENTS + "/{docId}")
    public String getDocForm(@PathVariable Long docId, ModelMap model) {

        DocumentDto documentDto;
        if (docId == -1) {
            documentDto = new DocumentDto();
            documentDto.setDocumentId(Long.valueOf(-1));
        } else {
            documentDto = documentsService.getAsDto(docId);
        }

        model.put("docCommand", documentDto);
        model.put("fileCommand", new FileDto());

        return Views.DOCUMENT_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@ModelAttribute("docCommand") DocumentDto documentDto) {

        if (documentDto == null) {
            throw new IllegalArgumentException("A documentDto is required");
        }

        LOG.debug("Submitted docId " + documentDto.getDocumentId());

        documentsService.saveFromDto(documentDto);

        return "redirect:" + Views.DOCUMENTS;
    }


    @RequestMapping(method = RequestMethod.POST, value = Views.DOCUMENTS + "/{docId}/fileUpload")
    public String fileUploaded(@ModelAttribute("fileCommand") FileDto fileCommand,
                               @PathVariable Long docId, BindingResult result) {
        MultipartFile file = fileCommand.getFile();
//        fileValidator.validate(uploadedFile, result);

        if (result.hasErrors()) {
            return Views.DOCUMENTS;
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        try {
            writeFile(file.getInputStream(), Files.newOutputStream(createFile(docId, extension)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:" + Views.DOCUMENTS;
    }

    private Path createFile(Long docId, String extension) throws IOException {
        String folderPath = File.separator + "home" +
                File.separator + "mrubtsov" +
                File.separator + "projects" +
                File.separator + "java" +
                File.separator + "docimages" +
                File.separator + docId;

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

