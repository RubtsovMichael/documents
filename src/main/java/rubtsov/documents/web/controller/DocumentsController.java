package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.service.DocumentsService;
import rubtsov.documents.web.Utils.Views;

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


}

