package com.codewithmosh.store.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDto {
    private OrderProductDto product;
    private int quantity;
    private BigDecimal totalPrice;

}
