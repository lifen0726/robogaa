package com.lifen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifen.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // 可以新增一些自訂的查詢方法
}

