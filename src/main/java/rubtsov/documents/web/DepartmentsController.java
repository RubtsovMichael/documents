package rubtsov.documents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.service.DepartmentsService;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 03.10.13
 * Time: 15:30
 */
@Controller
@RequestMapping(Views.DEPARTMENTS)
public class DepartmentsController {

    @Autowired
    DepartmentsService departmentsService;

    @RequestMapping(method = RequestMethod.GET)
    public String departments(Model model) {

        model.addAttribute("departments", Arrays.toString(departmentsService.getAllDepartments().toArray()));

        return Views.DEPARTMENTS;
    }

}