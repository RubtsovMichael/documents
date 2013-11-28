package rubtsov.documents.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.data.model.dto.EmployeeDto;
import rubtsov.documents.service.EmployeesService;
import rubtsov.documents.web.Utils.Views;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 28.11.13
 * Time: 18:08
 */
@Controller
@RequestMapping("/employee**")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @RequestMapping(method = RequestMethod.GET, value = Views.EMPLOYEES + "/{employeeId}")
    public String getCaseForm(@PathVariable Long employeeId, ModelMap model) {

        EmployeeDto employeeDto;
        if (employeeId == -1) {
            employeeDto = new EmployeeDto();
            employeeDto.setEmployeeId(Long.valueOf(-1));
        } else {
            employeeDto = employeesService.getAsDto(employeeId);
        }

        model.put("employeeCommand", employeeDto);

        return Views.EMPLOYEE_FORM;
    }


}