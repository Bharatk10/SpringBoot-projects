package com.zettamine.boot;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Serializable> {

}
