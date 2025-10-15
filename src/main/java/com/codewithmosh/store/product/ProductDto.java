package com.codewithmosh.store.dtos;

import com.codewithmosh.store.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Byte categoryId;
    private BigDecimal price;
}
