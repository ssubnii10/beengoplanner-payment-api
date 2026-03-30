package com.beengoplanner.payment_api.service;

import com.beengoplanner.payment_api.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class HighAmountRule implements RiskRule {

    @Override
    public int evaluate(Payment payment) {
        // 정상 금액 범위 벗어나면 리스크 점수 추가
        // 월 구독: 9900, 연 구독: 99000
        if (payment.getAmount() != 9900 && payment.getAmount() != 99000) {
            return 50; // 비정상 금액 → 거절
        }
        return 0; // 정상 금액 → 승인
    }
}