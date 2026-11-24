package com.example.demo.payment.application.port.out;

import com.example.demo.payment.domain.enums.PgType;
import com.example.demo.payment.domain.vo.PaymentResult;

public interface PaymentGatewayPort {
    PgType type();
    PaymentResult success();
    PaymentResult fail();
}
