package com.codewithmosh.store.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemsDto> items;
    private BigDecimal totalPrice;
}
