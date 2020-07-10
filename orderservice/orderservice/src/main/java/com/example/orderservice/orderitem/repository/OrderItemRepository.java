package com.example.orderservice.orderitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.orderitem.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
