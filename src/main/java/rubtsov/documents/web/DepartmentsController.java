package rubtsov.documents.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.Department;
import rubtsov.documents.service.DepartmentsService;

import java.util.ArrayList;
import java.util.Arrays;

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
    DepartmentsService departmentsService;

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENTS)
    public String departments(Model model) {

        model.addAttribute("departments", departmentsService.getAllDepartments());

        return Views.DEPARTMENTS;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENT_FORM)
    public String departmentForm(Model model) {

        model.addAttribute("departmentCommand", new DepartmentDto());

        return "/department";
    }

    @RequestMapping(value = Views.DEPARTMENT_FORM, method = RequestMethod.POST)
    public String create(
            @ModelAttribute("departmentCommand") final DepartmentDto departmentCommand) {
        if (departmentCommand == null) {
            throw new IllegalArgumentException("A departmentDto is required");
        }

        LOG.debug(departmentCommand.toString());
        return "redirect:" + Views.DEPARTMENTS;
    }

}
