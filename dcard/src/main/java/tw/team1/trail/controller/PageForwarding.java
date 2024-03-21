package tw.team1.trail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageForwarding {
    @GetMapping("/testCookies2")
    public ModelAndView testCookies() {
            String pageName = "testCookies";
        return new ModelAndView("forward:/trail/test/" + pageName + ".html");
    }
}
