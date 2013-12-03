package rubtsov.documents.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 17.10.13
 * Time: 17:46
 */
@Controller
//@RequestMapping(value = "/student")
public class StudentController {

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        Student student = new Student();
        student.setId(1);
        student.setAge(3);
        student.setName("sasdasd");
        student.setBirth(new Date());
        model.put("student", student);
        return "student";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student, BindingResult result, ModelMap model) {
        model.addAttribute("name", student.getName());
        model.addAttribute("age", student.getAge());
        model.addAttribute("id", student.getId());

        return "result";
    }
}