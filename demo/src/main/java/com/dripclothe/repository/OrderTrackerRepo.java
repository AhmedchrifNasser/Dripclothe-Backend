package com.dripclothe.repository;

import com.dripclothe.model.OrderTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTrackerRepo  extends JpaRepository<OrderTracker, Integer> {
}
