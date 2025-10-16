package com.codewithmosh.store.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
    Product toRegisterProduct(ProductDto request);

    void toUpdateProduct(RegisterProductRequest request, @MappingTarget Product product);
}

