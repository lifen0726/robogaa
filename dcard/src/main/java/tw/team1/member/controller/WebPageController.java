package tw.team1.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {
	

	
	@GetMapping(value = {"/", "/index"})
    public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	
}
