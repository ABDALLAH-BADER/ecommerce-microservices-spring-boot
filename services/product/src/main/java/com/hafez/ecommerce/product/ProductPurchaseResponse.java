package com.hafez.ecommerce.product;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {
}
