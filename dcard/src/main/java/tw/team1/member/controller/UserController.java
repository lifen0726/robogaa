package tw.team1.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.team1.member.model.Member;
import tw.team1.member.service.UserProfilesService;

@RestController
public class UserController {
    @Autowired
    UserProfilesService userProfilesService;

    // 獲取用戶名稱
    @GetMapping("/getUsername")
    public String getUsername(Authentication authentication) {
        Member userProfiles = getUserProfiles(authentication);
        return userProfiles != null ? userProfiles.getUsername() : "";
    }

    // 檢查是否具有管理員權限
    @GetMapping("/isAdmin")
    public boolean isAdmin(Authentication authentication) {
        Member userProfiles = getUserProfiles(authentication);
        boolean isAdmin = userProfiles != null && userProfiles.isAdmin();
        System.out.println(isAdmin);
        return isAdmin;
    }

    //顯示會員資訊,json格式
    @ResponseBody
	@GetMapping("/getMemberProfile")
    public Member getMemberProfile(Authentication authentication) {
		Member userProfiles = getUserProfiles(authentication);
		String memberProfile =userProfiles.toString();
		System.out.println(memberProfile);
		return userProfiles;
    }



    // 私有方法，用於獲取用戶資料
    private Member getUserProfiles(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String account = authentication.getName();
            System.out.println(account);
            return userProfilesService.findNameByAccount(account);
        }
        return null;
    }
}
