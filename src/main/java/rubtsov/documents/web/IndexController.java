package rubtsov.documents.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rubtsov.documents.web.Utils.Views;

/**
 * Created with IntelliJ IDEA.
 * User: mrubtsov
 * Date: 14.10.13
 * Time: 12:18
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return Views.INDEX;
    }

}
