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
import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.data.model.entity.CaseFolder;
import rubtsov.documents.data.model.entity.Correspondent;
import rubtsov.documents.service.CasesService;
import rubtsov.documents.service.CorrespondentsService;
import rubtsov.documents.web.Utils.Views;

/**
 * Created by mike on 25.10.13.
 */
@Controller
@RequestMapping(Views.CASE_FOLDERS + "/**")
public class CasesController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(PostsController.class);

    @Autowired
    private CasesService casesService;

    @Autowired
    private CorrespondentsService correspondentsService;

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
            CaseFolder caseFolder = casesService.load(caseId);
            if (caseFolder == null) {
                throw new IllegalArgumentException("Case folder ID is not found");
            }
            caseFolderDto = new CaseFolderDto(caseFolder);
        }

        model.put("caseCommand", caseFolderDto);

        return Views.CASE_FOLDER_FORM;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.CASE_FOLDERS + "/{caseId}/corr/{corrId}")
    public String getCorrespondentForm(@PathVariable Long caseId, @PathVariable Long corrId, ModelMap model) {

        CorrespondentDto correspondentDto;
        if (corrId == -1) {
            correspondentDto = new CorrespondentDto();
            correspondentDto.setCorrespondentId(Long.valueOf(-1));
            correspondentDto.setCaseFolder(new CaseFolderDto(casesService.load(caseId)));
        } else {
            Correspondent correspondent = correspondentsService.load(corrId);
            if (correspondent == null) {
                throw new IllegalArgumentException("Case folder ID is not found");
            }
            correspondentDto = new CorrespondentDto (correspondent);
        }

        model.put("correspondentCommand", correspondentDto);

        return Views.CORRESPONDENT_FORM;
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

    @RequestMapping(method = RequestMethod.POST, value = Views.CASE_FOLDERS + "/**/corr/**")
    public String saveCorrespondent(@ModelAttribute("correspondentCommand") CorrespondentDto correspondentDto) {

        if (correspondentDto == null) {
            throw new IllegalArgumentException("A corrspondentDto is required");
        }

        LOG.debug("Submitted corrspondentId " + correspondentDto.getCorrespondentId());

        correspondentsService.saveFromDto(correspondentDto);

        return "redirect:" + Views.CASE_FOLDERS;
    }

}
