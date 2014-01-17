package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.dto.CaseFolderDto;
import rubtsov.documents.data.model.dto.CorrespondentDto;
import rubtsov.documents.service.CasesService;
import rubtsov.documents.service.CorrespondentsService;
import rubtsov.documents.web.utils.Views;

/**
 * Created by mrubtsov on 17.01.14.
 */
@Controller
public class CorrespondentsController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(CorrespondentsController.class);

    @Autowired
    private CasesService casesService;

    @Autowired
    private CorrespondentsService correspondentsService;

    @RequestMapping(method = RequestMethod.GET, value = Views.CASE_FOLDERS + "/{caseId}"+ Views.CORRESPONDENTS +"/{corrId}")
    public String getCorrespondentForm(@PathVariable Long caseId, @PathVariable Long corrId, ModelMap model) {

        CorrespondentDto correspondentDto;
        if (corrId == -1) {
            correspondentDto = new CorrespondentDto();
            correspondentDto.setCorrespondentId(Long.valueOf(-1));
            correspondentDto.setCaseFolder(new CaseFolderDto(casesService.load(caseId)));
        } else {
            correspondentDto = correspondentsService.getAsDto(corrId);
        }

        model.put("correspondentCommand", correspondentDto);

        return Views.CORRESPONDENT_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.CASE_FOLDERS + "/**"+ Views.CORRESPONDENTS +"/**")
    public String saveCorrespondent(@ModelAttribute("correspondentCommand") CorrespondentDto correspondentDto, BindingResult result) {

        if (correspondentDto == null) {
            throw new IllegalArgumentException("A corrspondentDto is required");
        }

        LOG.debug("Submitted corrspondentId " + correspondentDto.getCorrespondentId());

        correspondentsService.saveFromDto(correspondentDto);

        return "redirect:" + Views.CASE_FOLDERS;
    }

}
