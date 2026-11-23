package com.example.demo.domain.payment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.demo.config.exception.CustomException;
import com.example.demo.config.exception.ErrorCode;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.payment.entity.SaleCode;
import com.example.demo.domain.payment.repository.PaymentRepository;
import com.example.demo.domain.payment.repository.SaleCodeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final SaleCodeRepository saleCodeRepository; 
    private final MemberRepository memberRepository;
    
    @Transactional 
    public Long create(Long userId, Long paymentAmount, String saleCodeString, BigDecimal charge){

        Member member = memberRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        SaleCode saleCode = null;
        if(!saleCodeString.isEmpty()){
            saleCode = saleCodeRepository.findByCode(saleCodeString).orElseThrow(()-> new CustomException(ErrorCode.DATA_INTEGRITY_VIOLATION));
        }
        Long saleAmount = this.calculateSaleAmount(paymentAmount, saleCode);
        Long chargeAmount = this.calculateChargeAmount(paymentAmount, saleAmount, charge);
        Long totalPaymentAmount = this.calculateTotalPaymentAmount(paymentAmount, saleAmount, chargeAmount);


        Payment payment = new Payment(member, paymentAmount, chargeAmount, saleAmount, totalPaymentAmount, saleCode);
        
        Long paymentId  = paymentRepository.save(payment).getId();

        return paymentId;

    }


    public Long paid(Long ) 
    public Long calculateTotalPaymentAmount(Long payment, Long saleAmount, Long chargeAmount){

        
        return 0L;

    }

    public Long calculateChargeAmount(Long payment, Long saleAmount, BigDecimal charge){

        
        return 0L;

    }

    
    public Long calculateSaleAmount(Long payment, SaleCode SalesCode){


        return 0L;

    }
}
