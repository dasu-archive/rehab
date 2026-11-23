package com.example.demo.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.payment.entity.Payment;




public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
