package com.zettamine.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.criteria.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
