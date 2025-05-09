package com.dripclothe.repository;

import com.dripclothe.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    Coupon findAllByName(String Name);
}
