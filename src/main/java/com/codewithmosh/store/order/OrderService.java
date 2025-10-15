package com.codewithmosh.store.order;

import com.codewithmosh.store.auth.AuthService;
import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.exceptions.OrderNotFoundException;
import com.codewithmosh.store.mappers.OrderMapper;
import com.codewithmosh.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private final AuthService authService;


    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);

        return orders.stream().map(orderMapper::toOrderDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItem(orderId)
                .orElseThrow(OrderNotFoundException::new);

        var user = authService.getCurrentUser();
        if(!order.isPlaceByCustomer(user)) {
            throw new AccessDeniedException("You don't have permission to access this order");
        }

        return orderMapper.toOrderDto(order);
    }
}
