package com.codewithmosh.store.carts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository  extends JpaRepository<CartItem, Byte> {

}
