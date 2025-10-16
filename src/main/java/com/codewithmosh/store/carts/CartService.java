package com.codewithmosh.store.carts;

import com.codewithmosh.store.product.ProductNotFoundException;
import com.codewithmosh.store.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);

        return cartMapper.toDto(cart);
    }

    public Iterable<CartDto> getAllCart() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
    }

    public CartDto getCartById(UUID cartId) {
        var cart =  cartRepository.getCartsWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, AddItemToCartRequest productId) {
        var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var product = productRepository.findById(productId.getProductId()).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);
        return cartMapper.toDtoItem(cartItem);
    }

    public CartItemDto updateCart(UUID cartId, Long productId, UpdateCartItemRequest request) {
        var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
        if(cart == null) {
            throw new CartNotFoundException();
        }

        var cartItem = cart.getItem(productId);

        if(cartItem == null) {
            throw new CartNotFoundException();
        }

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());

        cartRepository.save(cart);

        return cartMapper.toDtoItem(cartItem);
    }

    public void removeCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
        if(cart == null) {
            throw new CartNotFoundException();
        }

        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.getCartsWithItems(cartId).orElse(null);
        if(cart == null) {
           throw new CartNotFoundException();
        }

        cart.clear();

        cartRepository.save(cart);
    }
}

