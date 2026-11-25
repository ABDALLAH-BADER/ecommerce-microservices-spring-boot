package com.example.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String CustomerFirstName,
        String CustomerLastName,
        String CustomerEmail
) {
}
