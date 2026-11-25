package com.hafez.ecommerce.orderLine;

import com.hafez.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.Id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .product_id(orderLineRequest.productId())
                .build();
    }


    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
