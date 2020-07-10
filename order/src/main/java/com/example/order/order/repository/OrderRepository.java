package com.example.order.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	

}
