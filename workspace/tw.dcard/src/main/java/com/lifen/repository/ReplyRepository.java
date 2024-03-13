package com.lifen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifen.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	List<Reply> findByThreadid(int threadid);
    // 可以新增一些自訂的查詢方法
}

