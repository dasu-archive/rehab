package com.example.demo.payment.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.example.demo.member.entity.Member;
import com.example.demo.payment.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class Payment {
    
    @Id 
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    public Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="member_id")
    public Member member;


    @Enumerated (EnumType.STRING)
    public PaymentStatus paymentStatus;

    // product code 를 추가해 event 외에 다른 것도 추가될 수 있도록 조정 

    public Long paymentAmount;

    public Long chargeAmount;

    public Long saleAmount;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="sale_code_id")
    public SaleCode saleCode;

    public Long totalPaymentAmount;

    public LocalDateTime createdDt;

    public LocalDateTime updatedDt;

    public Payment(Member member, Long paymentAmount, Long chargeAmount, Long saleAmount, Long totalPaymentAmount, SaleCode saleCode){
        this.member = member;
        this.paymentAmount = paymentAmount;
        this.chargeAmount = chargeAmount;
        this.saleAmount = saleAmount;
        this.saleCode = Objects.isNull(saleCode) ? null : saleCode;
        this.totalPaymentAmount = totalPaymentAmount;
        this.createdDt = LocalDateTime.now();
        this.updatedDt = LocalDateTime.now();
    }

}
