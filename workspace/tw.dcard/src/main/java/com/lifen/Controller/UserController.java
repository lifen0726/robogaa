package com.lifen.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lifen.model.Member;
import com.lifen.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Member> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Member getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public Member saveUser(@RequestBody Member user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}

