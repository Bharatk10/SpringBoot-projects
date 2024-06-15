package com.zettamine.boot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.security.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
