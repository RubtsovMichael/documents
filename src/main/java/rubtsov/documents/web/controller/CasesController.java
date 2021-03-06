package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.dto.CaseFolderDto;
import rubtsov.documents.service.CasesService;
import rubtsov.documents.web.utils.Views;

/**
 * Created by mike on 25.10.13.
 */
@Controller
@RequestMapping(Views.CASE_FOLDERS + "/**")
public class CasesController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(CasesController.class);

    @Autowired
    private CasesService casesService;

    @RequestMapping(method = RequestMethod.GET, value = Views.CASE_FOLDERS)
    public String getPosts(ModelMap model) {

        model.put("caseFolders", casesService.getAllCaseFoldersDtos());

        return Views.CASE_FOLDERS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.CASE_FOLDERS + "/{caseId}")
    public String getCaseForm(@PathVariable Long caseId, ModelMap model) {

        CaseFolderDto caseFolderDto;
        if (caseId == -1) {
            caseFolderDto = new CaseFolderDto();
            caseFolderDto.setCaseId(Long.valueOf(-1));
        } else {
            caseFolderDto = casesService.getAsDto(caseId);
        }

        model.put("caseCommand", caseFolderDto);

        return Views.CASE_FOLDER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.CASE_FOLDERS + "/*")
    public String saveCaseFolder(@ModelAttribute("caseCommand") CaseFolderDto caseFolderDto) {

        if (caseFolderDto == null) {
            throw new IllegalArgumentException("A caseDto is required");
        }

        LOG.debug("Submitted caseId " + caseFolderDto.getCaseId());

        casesService.saveFromDto(caseFolderDto);

        return "redirect:" + Views.CASE_FOLDERS;
    }

}
