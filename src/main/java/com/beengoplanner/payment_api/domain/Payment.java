package com.beengoplanner.payment_api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String planType; // MONTHLY, YEARLY
    private int amount;
    private int riskScore;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}