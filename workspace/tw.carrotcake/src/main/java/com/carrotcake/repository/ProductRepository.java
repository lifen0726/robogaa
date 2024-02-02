package com.carrotcake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrotcake.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    // 这里可以根据需要定义查询方法，Spring Data JPA会自动实现这些方法
}
