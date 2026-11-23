package com.example.demo.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.payment.entity.PaymentMethod;


@Repository 
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>{
    
}
