package com.example.demo.payment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.payment.entity.Payment;
import com.example.demo.domain.payment.entity.SaleCode;
import com.example.demo.domain.payment.repository.PaymentRepository;
import com.example.demo.domain.payment.repository.SaleCodeRepository;
import com.example.demo.domain.payment.utils.PaymentCalculator;
import com.example.demo.global.config.exception.CustomException;
import com.example.demo.global.config.exception.ErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final SaleCodeRepository saleCodeRepository; 
    private final MemberRepository memberRepository;
    private final PaymentCalculator paymentCalculator;
    
    @Transactional 
    public Long create(Long userId, Long paymentAmount, String saleCodeString, BigDecimal charge){

        Member member = memberRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        SaleCode saleCode = null;
        if(!saleCodeString.isEmpty()){
            saleCode = saleCodeRepository.findByCode(saleCodeString).orElseThrow(()-> new CustomException(ErrorCode.DATA_INTEGRITY_VIOLATION));
        }
        Long saleAmount = PaymentCalculator.calculateSaleAmount(paymentAmount, saleCode);
        Long chargeAmount = PaymentCalculator.calculateChargeAmount(paymentAmount, saleAmount, charge);
        Long totalPaymentAmount = PaymentCalculator.calculateTotalPaymentAmount(paymentAmount, saleAmount, chargeAmount);


        Payment payment = new Payment(member, paymentAmount, chargeAmount, saleAmount, totalPaymentAmount, saleCode);
        
        Long paymentId  = paymentRepository.save(payment).getId();

        return paymentId;

    }

    // payment 의 모든 결과 처리
    @Transactional 
    public Long paid_result(Long paymentId){


        // 성공

        // 실패 
        return 0L;
    } 





}
