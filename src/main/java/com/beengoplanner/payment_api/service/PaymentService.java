package com.beengoplanner.payment_api.service;

import com.beengoplanner.payment_api.domain.Payment;
import com.beengoplanner.payment_api.domain.PaymentStatus;
import com.beengoplanner.payment_api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final List<RiskRule> riskRules;

    @Transactional
    public Payment processPayment(Long userId, String planType, int amount) {

        // 리스크 점수 계산
        int riskScore = riskRules.stream()
                .mapToInt(rule -> rule.evaluate(
                        Payment.builder()
                                .userId(userId)
                                .planType(planType)
                                .amount(amount)
                                .build()
                ))
                .sum();

        // 리스크 점수 기준으로 승인/거절 결정
        PaymentStatus status = riskScore >= 50
                ? PaymentStatus.REJECTED
                : PaymentStatus.APPROVED;

        // 결제 저장
        Payment payment = Payment.builder()
                .userId(userId)
                .planType(planType)
                .amount(amount)
                .riskScore(riskScore)
                .status(status)
                .build();

        return paymentRepository.save(payment);
    }
}