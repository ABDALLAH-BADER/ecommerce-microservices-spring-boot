package com.hafez.ecommerce.order;

import com.hafez.ecommerce.customer.CustomerClient;
import com.hafez.ecommerce.exception.BusinessException;
import com.hafez.ecommerce.kafka.OrderConfirmation;
import com.hafez.ecommerce.kafka.OrderProducer;
import com.hafez.ecommerce.orderLine.OrderLineRequest;
import com.hafez.ecommerce.orderLine.OrderLineService;
import com.hafez.ecommerce.payment.PaymentClient;
import com.hafez.ecommerce.payment.PaymentRequest;
import com.hafez.ecommerce.product.ProductClient;
import com.hafez.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createdOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order:: No Customer exists with the provided id"));

        var purchasedProducts= this.productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
     return order.getId();
    }



    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("no order found with the provided id %d ",orderId)));
    }
}
