package rubtsov.documents.web.controller;

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
import java.text.SimpleDateFormat;
import java.util.Date;

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


    @RequestMapping(method = RequestMethod.POST, value = Views.DOCUMENTS + "/fileUpload")
    public String fileUploaded(
            @ModelAttribute("fileCommand") FileDto fileCommand,
            BindingResult result) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        MultipartFile file = fileCommand.getFile();
//        fileValidator.validate(uploadedFile, result);

        String fileName = file.getOriginalFilename();

        if (result.hasErrors()) {
            return Views.DOCUMENTS;
        }

        try {
            inputStream = file.getInputStream();

            File newFile = new File("/home/mrubtsov/projects/java/" + fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:" + Views.DOCUMENTS;
    }

}

