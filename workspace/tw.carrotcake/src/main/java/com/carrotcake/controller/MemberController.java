package com.carrotcake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.carrotcake.exception.UserNotFoundException;
import com.carrotcake.model.Member;
import com.carrotcake.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberName}")
    public ResponseEntity<Member> getMemberByName(@PathVariable String memberName) {
        try {
            Member member = memberService.getMemberByName(memberName);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public String saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return "Insert OK";
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
