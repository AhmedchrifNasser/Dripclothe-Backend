package com.dripclothe.dto;

import org.springframework.lang.NonNull;

public class PurchaseResponse {
    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    private final String orderTrackingNumber;

    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
