package com.carrotcake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrotcake.exception.UserNotFoundException;
import com.carrotcake.model.Member;
import com.carrotcake.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

	@Autowired 
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberByName(String name) {
        Optional<Member> uResp = memberRepository.findByName(name);
        if(uResp.isEmpty()) {
        	throw new UsernameNotFoundException("Can't find member.");
        }
        return uResp.get();
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(int memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member getMemberById(int memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {
            throw new UserNotFoundException("Member not found with ID: " + memberId);
        }
    }

}

