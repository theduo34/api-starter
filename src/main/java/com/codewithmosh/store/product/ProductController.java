package com.codewithmosh.store.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product")
public class ProductController {
   private final ProductRepository productRepository;
   private final ProductMapper productMapper;
   private final CategoryRepository categoryRepository;

    @GetMapping
   public Iterable<ProductDto> getAllProducts (@RequestParam(
           required = false, name = "categoryId" ) Byte categoryId) {
       List<Product> products;

       if(categoryId != null) {
           products = productRepository.findByCategoryId(categoryId);
       } else {
           products = productRepository.findAllWithCategory();
       }

       return products.stream().map(productMapper::toDto).collect(Collectors.toList());
   }

   @GetMapping("/{productId}")
   public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
       var product = productRepository.findById(productId).orElse(null);
       if(product == null) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(productMapper.toDto(product));
   }

   @PostMapping
   public ResponseEntity<ProductDto> createProduct(
           @RequestBody ProductDto request,
           UriComponentsBuilder uriBuilder
   ) {
       var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
       if(category == null) {
           return ResponseEntity.notFound().build();
       }
       var product = productMapper.toRegisterProduct(request);
       product.setCategory(category);
       productRepository.save(product);
       //product.setId(product.getId());

       var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

       var productDto =  productMapper.toDto(product);
       return ResponseEntity.created(uri).body(productDto);
   }

   @PutMapping("/{productId}")
   public ResponseEntity<ProductDto> updateProduct(
           @PathVariable Long productId,
           @RequestBody RegisterProductRequest request) {
        var category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if(category == null) {
            return ResponseEntity.notFound().build();
        }

       var product = productRepository.findById(productId).orElse(null);
       if(product == null) {
           return ResponseEntity.notFound().build();
       }

       productMapper.toUpdateProduct(request,product);
       product.setCategory(category);

       var savedProduct = productRepository.save(product);
       var responseDto = productMapper.toDto(savedProduct);

       return ResponseEntity.ok(responseDto);
   }

   @DeleteMapping("/{productId}")
   public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
       var product = productRepository.findById(productId).orElse(null);
       if(product == null) {
           return ResponseEntity.notFound().build();
       }
       productRepository.delete(product);
       return ResponseEntity.noContent().build();
   }
}
