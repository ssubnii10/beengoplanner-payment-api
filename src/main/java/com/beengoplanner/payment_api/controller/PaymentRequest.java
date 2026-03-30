package com.beengoplanner.payment_api.controller;

import lombok.Getter;

@Getter
public class PaymentRequest {
    private Long userId;
    private String planType;
    private int amount;
}