package com.hafez.ecommerce.payment;

import com.hafez.ecommerce.customer.CustomerResponse;
import com.hafez.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
