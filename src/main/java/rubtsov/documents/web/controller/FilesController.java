package rubtsov.documents.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.dto.FileDto;
import rubtsov.documents.service.DocumentsService;
import rubtsov.documents.service.FileUploadService;
import rubtsov.documents.validator.FileValidator;
import rubtsov.documents.web.utils.Views;

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
