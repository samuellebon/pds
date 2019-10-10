package kan10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Homepage Menu
 * @author Suriya
 */
@Controller
public class HomePageController {

    /**
     * Home page view
     * @return
     */
    @RequestMapping(value = {"/"})
    String index() {
       return "index";
    }
}