package rubtsov.documents.web.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.dto.FileDto;
import rubtsov.documents.service.DocumentsService;
import rubtsov.documents.service.FileUploadService;
import rubtsov.documents.validator.FileValidator;
import rubtsov.documents.web.utils.Views;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mrubtsov on 19.12.13.
 */
@Controller
public class FilesController {

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    DocumentsService documentsService;

    @Autowired
    FileValidator fileValidator;

    @RequestMapping(method = RequestMethod.GET, value = Views.DOCUMENTS + "/{docId}/fileUpload")
    public String getDocForm(@PathVariable Long docId, ModelMap model) {
        DocumentDto documentDto = documentsService.getAsDto(docId);

        FileDto fileDto = new FileDto();
        fileDto.setDocument(documentDto);

        model.put("fileCommand", fileDto);

        return Views.FILE_UPLOAD_FORM;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.DOCUMENTS + "/{docId}/getImage")
    public @ResponseBody byte[] getDocImage(@PathVariable Long docId, ModelMap model) throws IOException {
        byte[] image = fileUploadService.getDocumentScaledImage(docId);

        if (image == null) {
            InputStream noPhoto = FilesController.class.getResourceAsStream("/no_photo.png");
            if (noPhoto != null ) {
                image = IOUtils.toByteArray(noPhoto);
            }
        }

        return image;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.DOCUMENTS + "/{docId}/fileUpload")
    public String fileUploaded(@ModelAttribute("fileCommand") FileDto fileCommand,
                               @PathVariable Long docId, BindingResult result) {
        MultipartFile file = fileCommand.getFile();
        fileValidator.validate(fileCommand, result);

        if (result.hasErrors()) {
            return Views.FILE_UPLOAD_FORM;
        }

        if (!fileUploadService.saveDocumentImage(docId, file)) {
            return Views.FILE_UPLOAD_FORM;
        }

        return "redirect:" + Views.DOCUMENTS + "/{docId}";
    }

}
