package tw.team1.trail.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrailPageController {
    @GetMapping("/testCookies")
    public String testCookies() {
        return "trail/testCookies";
    }

}
