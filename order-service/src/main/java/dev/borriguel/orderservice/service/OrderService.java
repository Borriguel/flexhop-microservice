package dev.borriguel.orderservice.service;

import dev.borriguel.orderservice.model.entity.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order findOrderById(Long id);
}
