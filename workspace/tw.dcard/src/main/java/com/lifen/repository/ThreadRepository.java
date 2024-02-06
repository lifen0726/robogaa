package com.lifen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lifen.model.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Integer> {
    // 可以新增一些自訂的查詢方法
}

