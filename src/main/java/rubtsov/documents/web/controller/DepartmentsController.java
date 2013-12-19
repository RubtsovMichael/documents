package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rubtsov.documents.service.DepartmentsService;
import rubtsov.documents.web.utils.Views;
import rubtsov.documents.data.model.dto.DepartmentDto;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 03.10.13
 * Time: 15:30
 */
@Controller
@RequestMapping(Views.DEPARTMENTS + "/**")
public class DepartmentsController {

    Logger LOG = LoggerFactory.getLogger(DepartmentsController.class);

    @Autowired
    private DepartmentsService departmentsService;

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENTS)
    public String departments(Model model) {

        model.addAttribute("departments", departmentsService.getAllDepartmentsDtos());

        return Views.DEPARTMENTS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENTS + "/{depId}" )
    public String departmentForm(@PathVariable Long depId, Model model) {

        DepartmentDto departmentDto;
        if (depId == -1) {
            departmentDto = new DepartmentDto();
            departmentDto.setDepartmentId(Long.valueOf(-1));
        } else {
            departmentDto = departmentsService.getAsDto(depId);
        }
        model.addAttribute("departmentCommand", departmentDto);
        return Views.DEPARTMENT_FORM;
    }

    @RequestMapping(value = Views.DEPARTMENTS + "/*", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("departmentCommand") final DepartmentDto departmentCommand) {
        if (departmentCommand == null) {
            throw new IllegalArgumentException("A departmentDto is required");
        }

        LOG.debug("Submitted depId " + departmentCommand.getDepartmentId());

        departmentsService.saveFromDto(departmentCommand);

        return "redirect:" + Views.DEPARTMENTS;
    }
}
