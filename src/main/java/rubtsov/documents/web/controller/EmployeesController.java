package rubtsov.documents.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.service.DepartmentsService;
import rubtsov.documents.service.EmployeesService;
import rubtsov.documents.service.PersonsService;
import rubtsov.documents.service.PostsService;
import rubtsov.documents.web.utils.Views;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 28.11.13
 * Time: 18:08
 */
@Controller
//@RequestMapping("**/" + Views.EMPLOYEES + "/**")
public class EmployeesController {

    Logger LOG = org.slf4j.LoggerFactory.getLogger(EmployeesController.class);

    @Autowired
    EmployeesService employeesService;

    @Autowired
    DepartmentsService departmentsService;

    @Autowired
    PostsService postsService;

    @Autowired
    PersonsService personsService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.DEPARTMENTS + "/{depId}" + Views.EMPLOYEES + "/{employeeId}")
    public String getDepEmployeeForm(@PathVariable Long depId, @PathVariable Long employeeId, ModelMap model) {
        EmployeeDto employeeDto;
        if (employeeId == -1) {
            employeeDto = new EmployeeDto();
            employeeDto.setEmployeeId(Long.valueOf(-1));
            employeeDto.setDepartmentId(depId);
        } else {
            employeeDto = employeesService.getAsDto(employeeId);
        }

        employeeDto.setFromDepartment(true);

        model.put("depts", departmentsService.getDeptsAsMap());
        model.put("posts", postsService.getPostsAsMap());
        model.put("persons", personsService.getPersonsAsMap());
        model.put("employeeCommand", employeeDto);

        return Views.EMPLOYEE_FORM;
    }

    @RequestMapping(method = RequestMethod.GET, value = Views.PERSONS + "/{personId}" + Views.EMPLOYEES + "/{employeeId}")
    public String getPersonEmployeeForm(@PathVariable Long personId, @PathVariable Long employeeId, ModelMap model) {
        EmployeeDto employeeDto;
        if (employeeId == -1) {
            employeeDto = new EmployeeDto();
            employeeDto.setEmployeeId(Long.valueOf(-1));
            employeeDto.setPersonId(personId);
        } else {
            employeeDto = employeesService.getAsDto(employeeId);
        }

        employeeDto.setFromPerson(true);

        model.put("depts", departmentsService.getDeptsAsMap());
        model.put("posts", postsService.getPostsAsMap());
        model.put("persons", personsService.getPersonsAsMap());
        model.put("employeeCommand", employeeDto);

        return Views.EMPLOYEE_FORM;
    }

    @RequestMapping(method = RequestMethod.POST, value = Views.DEPARTMENTS + "/{depId}" + Views.EMPLOYEES + "/*")
    public String saveEmployee(@PathVariable Long depId,
            @ModelAttribute("employeeCommand") EmployeeDto employeeDto, BindingResult result) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("An employeeDto is required");
        }

        LOG.debug("Submitted employeeId " + employeeDto.getEmployeeId());

        employeesService.saveFromDto(employeeDto);

        return "redirect:" + Views.DEPARTMENTS + "/{depId}";
    }
}
