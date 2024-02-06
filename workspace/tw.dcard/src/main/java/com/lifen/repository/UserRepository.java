package com.lifen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifen.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 可以新增一些自訂的查詢方法
}

