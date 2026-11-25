package com.hafez.ecommerce.orderLine;

public record OrderLineRequest(
        Integer Id,
        Integer orderId,
        Integer productId,
        double quantity) {
}
