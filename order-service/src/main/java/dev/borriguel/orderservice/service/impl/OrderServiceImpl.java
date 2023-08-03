package dev.borriguel.orderservice.service.impl;

import dev.borriguel.orderservice.client.ProductClient;
import dev.borriguel.orderservice.exception.NotFoundException;
import dev.borriguel.orderservice.model.entity.Order;
import dev.borriguel.orderservice.repository.OrderRepository;
import dev.borriguel.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final ProductClient productClient;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order: {}", order);
        productClient.reduceQuantity(order.getProductId(), order.getQuantity());
        var product = productClient.findProductById(order.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        log.info("Product found: {}", product);
        order.setTotalAmount(product.price().multiply(BigDecimal.valueOf(order.getQuantity())));
        return repository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
