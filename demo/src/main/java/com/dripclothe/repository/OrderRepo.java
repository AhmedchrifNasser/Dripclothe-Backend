package com.dripclothe.repository;

import com.dripclothe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    Optional<Order> findByorderTrackingNumber(String orderTrackingNumber);
}
