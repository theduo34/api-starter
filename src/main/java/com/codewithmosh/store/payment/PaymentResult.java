package com.codewithmosh.store.payment;

import com.codewithmosh.store.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentResult {
    private Long orderId;
    private OrderStatus paymentStatus;
}
