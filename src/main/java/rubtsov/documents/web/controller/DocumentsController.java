package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rubtsov.documents.data.model.dto.DocumentDto;
import rubtsov.documents.data.model.entity.DocumentType;
import rubtsov.documents.service.CasesService;
import rubtsov.documents.service.CorrespondentsService;
import rubtsov.documents.service.DocumentsService;
import rubtsov.documents.service.PersonsService;
import rubtsov.documents.web.Commands.DocControlsCommand;
import rubtsov.documents.web.utils.Views;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mike on 11.12.13.
 */
@Controller
@RequestMapping(Views.DOCUMENTS + "/**")
@SessionAttributes({"docCommand", "docControlsCommand", "caseFolders", "docTypes", "authors"})
public class DocumentsController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(DocumentsController.class);

    @Autowired
    DocumentsService documentsService;

    @Autowired
    CasesService casesService;

    @Autowired
    CorrespondentsService correspondentsService;

    @Autowired
    PersonsService personsService;

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

        Long caseId = documentDto.getCorrespondent() == null ? Long.valueOf(-1) : documentDto.getCorrespondent().getCaseFolder().getCaseId();

        model.put("docControlsCommand", new DocControlsCommand(caseId, documentDto.getDocType()));
        model.put("docCommand", documentDto);
        model.put("caseFolders", casesService.getCasesAsMap());
        model.put("docTypes", DocumentType.getAsMap());
        model.put("correspondents", correspondentsService.getAsMapByCaseFolderId(caseId));
        model.put("authors", personsService.getPersonsAsMap());

        return Views.DOCUMENT_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.DOCUMENTS + "/{docId}/controlsChanged")
    public String caseChanged(DocControlsCommand docControlsCommand, ModelMap model) {
        model.put("correspondents", correspondentsService.getAsMapByCaseFolderId(docControlsCommand.getCaseId()));
        return Views.DOCUMENT_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.DOCUMENTS + "/{docId}/save")
    public String save(DocumentDto documentDto) {

        if (documentDto == null) {
            throw new IllegalArgumentException("A documentDto is required");
        }

        LOG.debug("Submitted docId " + documentDto.getDocumentId());

        documentsService.saveFromDto(documentDto);

        return "redirect:" + Views.DOCUMENTS;
    }

}

