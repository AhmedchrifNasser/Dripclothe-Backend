package com.dripclothe.service;

import com.dripclothe.model.Coupon;
import com.dripclothe.model.CouponUsage;
import com.dripclothe.repository.CouponRepo;
import com.dripclothe.repository.CouponUsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponService {
    private final CouponRepo couponRepo;
    private final CouponUsageRepository couponUsageRepository;

    public CouponService(CouponRepo couponRepo, CouponUsageRepository couponUsageRepository) {
        this.couponRepo = couponRepo;
        this.couponUsageRepository = couponUsageRepository;
    }

    public Coupon verifyCoupon(String couponName){
        if (couponName == null || couponName.isEmpty()){
            return null;
        } else {
            return couponRepo.findAllByName(couponName);
        }
    }
    public boolean isCouponUsed(Coupon coupon, String userEmail) {
        return couponUsageRepository.existsByCouponAndUserEmail(coupon, userEmail);
    }
    public void registerCouponUsage(Coupon coupon, String userEmail) {
        CouponUsage usage = new CouponUsage(userEmail, coupon, LocalDateTime.now());
        couponUsageRepository.save(usage);
    }
}
