package rubtsov.documents.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rubtsov.documents.data.model.Department;
import rubtsov.documents.service.DepartmentsService;

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

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENT_FORM )
    public String departmentForm(@RequestParam(Views.DEP_ID_PARAM) Long depId, Model model) {

        Department dep = departmentsService.load(depId);

        if (dep == null) {
            throw new IllegalArgumentException("Department ID is not found");
        }

        model.addAttribute("departmentCommand", new DepartmentDto(dep));
        model.addAttribute("employees", dep.getEmployees());
        return Views.DEPARTMENT_FORM;
    }

    @RequestMapping(value = Views.DEPARTMENT_FORM, method = RequestMethod.POST)
    public String create(
            @ModelAttribute("departmentCommand") final DepartmentDto departmentCommand) {
        if (departmentCommand == null) {
            throw new IllegalArgumentException("A departmentDto is required");
        }

        LOG.debug(departmentCommand.toString());
        LOG.debug(String.valueOf(departmentCommand.getDepartmentId()));
        return "redirect:" + Views.DEPARTMENTS;
    }

}
