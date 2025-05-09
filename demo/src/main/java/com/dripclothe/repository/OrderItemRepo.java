package com.dripclothe.repository;

import com.dripclothe.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findOrderItemByOrderId(Integer orderId);
}
