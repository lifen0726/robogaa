package tw.team1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tw.team1.member.model.Member;
import tw.team1.member.model.VerificationToken;
import tw.team1.member.model.VerificationTokenRepository;
import tw.team1.member.service.MembersService;

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

	@GetMapping("/member")
	public String member() { return "member";}

	@Autowired
	private VerificationTokenRepository tokenRepository;
	@Autowired
	private MembersService membersService;

	@GetMapping("/verify/{token}")
	public String verifyMember(@PathVariable String token) {
		// 獲取會員實體,例如從請求體或會話中獲取
		VerificationToken verificationToken = tokenRepository.findByToken(token);
		if (verificationToken == null) {
			return "token is invalid";
		}
		Member member = verificationToken.getMember();
		membersService.verifyMember(token, member);
		return "register";
	}
}
