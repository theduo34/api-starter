package com.codewithmosh.store.carts;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    @EntityGraph(attributePaths = "cartItems.product")
    @Query("SELECT c from Cart c where c.id = :cartId")
    Optional<Cart> getCartsWithItems(@Param("cartId") UUID cartId);
}
