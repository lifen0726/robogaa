package tw.team1.trail.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrailPageController {
    @GetMapping("/toTestVue")
    public String toTestVue() {
        return "trail/testVue";
    }
    @GetMapping("/testCookies")
    public String testCookies() {
        return "trail/testCookies";
    }
    @GetMapping("/toAdminPage")
    public String toAdminPage() {
        return "trail/admin/testVue_v2_async";
    }
    @GetMapping("/toCustomerPage")
    public String toCustomerPage() {
        return "trail/customer/googleMapSearchView_likedTrail_v2";
    }
    @GetMapping("/toDetailPage")
    public String toDetailPage() {
        return "trail/customer/TrailDetail";
    }

    @GetMapping("/toIndexTest")
    public String toIndexTest() {
        return "index_trailCustomer";
    }
}
