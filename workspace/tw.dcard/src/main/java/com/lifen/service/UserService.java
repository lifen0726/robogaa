package com.lifen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifen.model.Member;
import com.lifen.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 可以在這裡添加與使用者相關的業務邏輯

    public List<Member> getAllUsers() {
        return userRepository.findAll();
    }

    public Member getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Member saveUser(Member user) {
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}

