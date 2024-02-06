package com.lifen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifen.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    // 可以新增一些自訂的查詢方法
}

