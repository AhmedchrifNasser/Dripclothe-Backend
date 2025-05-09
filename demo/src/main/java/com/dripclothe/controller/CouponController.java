package com.dripclothe.controller;

import com.dripclothe.model.Coupon;
import com.dripclothe.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/backend/coupon")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/find/{couponName}")
    public ResponseEntity<Coupon> getCouponByName(@PathVariable("couponName") String couponName){
        Coupon coupon = couponService.verifyCoupon(couponName);
        return new ResponseEntity<>(coupon, HttpStatus.OK);
    }

    @GetMapping("/used")
    public ResponseEntity<Boolean> isCouponUsed( @RequestParam Integer id, @RequestParam Double value,@RequestParam String name,
                                                 @RequestParam Boolean available,  @RequestParam String email){
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setValue(value);
        coupon.setName(name);
        coupon.setAvailable(available);
        Boolean check =  couponService.isCouponUsed(coupon, email);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCoupon(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");

        @SuppressWarnings("unchecked")
        Map<String, Object> couponMap = (Map<String, Object>) payload.get("coupon");

        Coupon coupon = new Coupon();
        coupon.setId((Integer) couponMap.get("id"));
        coupon.setName((String) couponMap.get("name"));
        coupon.setValue((Double) couponMap.get("value"));
        coupon.setAvailable((Boolean) couponMap.get("available"));

        try {
            couponService.registerCouponUsage(coupon, email);
            return new ResponseEntity<>("Coupon applied successfully.",HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
