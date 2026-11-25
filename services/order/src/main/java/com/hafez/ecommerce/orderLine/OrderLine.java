package com.hafez.ecommerce.orderLine;

import com.hafez.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer product_id;
    private double quantity;
}
