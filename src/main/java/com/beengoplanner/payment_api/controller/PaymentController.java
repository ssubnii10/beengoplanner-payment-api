package com.beengoplanner.payment_api.controller;

import com.beengoplanner.payment_api.domain.Payment;
import com.beengoplanner.payment_api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(
                request.getUserId(),
                request.getPlanType(),
                request.getAmount()
        );
    }
}