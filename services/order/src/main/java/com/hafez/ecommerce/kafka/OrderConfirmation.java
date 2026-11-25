package com.hafez.ecommerce.kafka;

import com.hafez.ecommerce.customer.CustomerResponse;
import com.hafez.ecommerce.order.PaymentMethod;
import com.hafez.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
