package com.lifen.Api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

    @GetMapping("/Category/{categoryName}")
    public ModelAndView getCategoryView(@PathVariable("categoryName") String categoryName) {
        // 內部轉發到靜態資源
        return new ModelAndView("forward:/" + categoryName + ".html");
    }
    
//    @GetMapping("/thread/{threadid}")
//    public ModelAndView getThreadView(@PathVariable("threadid") String threadId) {
//        // 內部轉發到靜態資源
//        return new ModelAndView("forward:/" + threadId + ".html");
//    }
}


