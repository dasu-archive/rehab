package com.example.demo.payment.domain.entity;

import com.example.demo.payment.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class PaymentTransaction extends  {
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    public Long id;


    // 변경 이력
    @Enumerated (EnumType.STRING)
    public PaymentStatus status;

    public 


    
    
}
