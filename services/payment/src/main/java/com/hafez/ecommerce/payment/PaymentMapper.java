package com.hafez.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(paymentRequest request) {
        return  Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}
