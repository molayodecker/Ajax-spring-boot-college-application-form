package gh.gov.moh.admissionsportal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by molayodecker on 20/01/2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

   /* @RequestMapping("/login")
    public String login(){
        return "login";
    }*/
}
