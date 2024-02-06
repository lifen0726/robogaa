package com.carrotcake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.carrotcake.exception.UserNotFoundException;
import com.carrotcake.model.Member;
import com.carrotcake.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @GetMapping("/{memberName}")
    public ResponseEntity<Member> getMemberByName(@PathVariable String memberName) {
        try {
            Member member = memberService.findByName(memberName);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        try {
            String username = request.getParameter("username");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");

            if (password != null && !password.trim().isEmpty()) {
                if (!memberService.existsByUsername(username)) {
                    Member member = new Member();
                    member.setName(username);
                    member.setPhone(phone);

                    // 在設置之前對密碼進行編碼
                    String encodedPassword = passwordEncoder.encode(password);
                    member.setPassword(encodedPassword);

                    memberService.saveMember(member);
                    return ResponseEntity.ok("User registered successfully");
                } else {
                    return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Password cannot be empty", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            // 對密碼進行編碼
            String encodedPassword = passwordEncoder.encode(password);

            // 使用 AuthenticationManager 進行身份驗證
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encodedPassword)
            );

            // 設定身份驗證結果到 Security 上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/login/welcome"; // 登入成功，重定向到 welcome 頁面
        } catch (Exception e) {
            return "redirect:/login/page?error=true"; // 登入失敗，重定向到 login 頁面並顯示錯誤
        }
    }


    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable int memberId, @RequestBody Member updatedMember) {
        try {
            Member existingMember = memberService.getMemberById(memberId);

            // Update member information
            existingMember.setName(updatedMember.getName());
            existingMember.setPassword(updatedMember.getPassword());
            existingMember.setPhone(updatedMember.getPhone());

            memberService.saveMember(existingMember);

            return new ResponseEntity<>("Update OK", HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable int memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
