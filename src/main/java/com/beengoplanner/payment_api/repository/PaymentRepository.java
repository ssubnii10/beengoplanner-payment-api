package com.beengoplanner.payment_api.repository;

import com.beengoplanner.payment_api.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}