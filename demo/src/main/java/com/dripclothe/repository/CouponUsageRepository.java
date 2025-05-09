package com.dripclothe.repository;

import com.dripclothe.model.Coupon;
import com.dripclothe.model.CouponUsage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponUsageRepository extends JpaRepository<CouponUsage, Long> {
    boolean existsByCouponAndUserEmail(Coupon coupon, String userEmail);
}
