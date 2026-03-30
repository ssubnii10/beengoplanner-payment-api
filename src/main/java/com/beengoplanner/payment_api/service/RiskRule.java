package com.beengoplanner.payment_api.service;

import com.beengoplanner.payment_api.domain.Payment;

public interface RiskRule {
    int evaluate(Payment payment);
}