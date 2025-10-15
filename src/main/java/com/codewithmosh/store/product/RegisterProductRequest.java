package com.codewithmosh.store.dtos;

import lombok.Data;

@Data
public class RegisterProductRequest {
    private String name;
    private String description;
    private Double price;
    private Byte categoryId;
}
