package com.example.demo.payment.adapter.out;

import com.example.demo.payment.application.port.out.PaymentGatewayPort;
import com.example.demo.payment.domain.enums.PgType;
import com.example.demo.payment.domain.vo.PaymentResult;

public class DefaultPaymentAdapter implements PaymentGatewayPort{

    @Override
    public PgType type() {
        return PgType.DEFAULT;
    }

    @Override
    public PaymentResult success() {
        
    }

    @Override
    public PaymentResult fail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fail'");
    }
    
}
