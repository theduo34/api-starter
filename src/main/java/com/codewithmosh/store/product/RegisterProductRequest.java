package com.codewithmosh.store.product;

import lombok.Data;

@Data
public class RegisterProductRequest {
    private String name;
    private String description;
    private Double price;
    private Byte categoryId;
}
