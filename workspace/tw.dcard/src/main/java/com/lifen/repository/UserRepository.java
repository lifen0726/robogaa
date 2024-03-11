package com.lifen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifen.model.Member;

public interface UserRepository extends JpaRepository<Member, Integer> {
    // 可以新增一些自訂的查詢方法
}

