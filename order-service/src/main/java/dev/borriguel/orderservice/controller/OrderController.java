package dev.borriguel.orderservice.controller;

import dev.borriguel.orderservice.client.PaymentClient;
import dev.borriguel.orderservice.client.ProductClient;
import dev.borriguel.orderservice.exception.NotFoundException;
import dev.borriguel.orderservice.model.dto.OrderDetailsResponse;
import dev.borriguel.orderservice.model.dto.OrderRequest;
import dev.borriguel.orderservice.model.dto.OrderResponse;
import dev.borriguel.orderservice.service.OrderService;
import dev.borriguel.orderservice.util.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService service;
    private final OrderMapper mapper;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = "201", description = "Order created", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        var order = mapper.toOrder(orderRequest);
        service.createOrder(order);
        log.info("Order created: {}", order);
        return mapper.toOrderResponse(order);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get order by ID")
    @ApiResponse(responseCode = "200", description = "Order found", content = @Content(schema = @Schema(implementation = OrderResponse.class)))
    public OrderResponse getOrderById(@PathVariable Long id) {
        var order = service.findOrderById(id);
        log.info("Order found: {}", order);
        return mapper.toOrderResponse(order);
    }

    @GetMapping("/details/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get order details by ID")
    @ApiResponse(responseCode = "200", description = "Order details found", content = @Content(schema = @Schema(implementation = OrderDetailsResponse.class)))
    public OrderDetailsResponse getOrderDetailsById(@PathVariable Long id) {
        var order = service.findOrderById(id);
        var productInfo = productClient.findProductById(order.getProductId()).orElseThrow(() -> new NotFoundException("Product not found"));
        var paymentInfo = paymentClient.getPaymentByOrderId(order.getOrderId());
        return new OrderDetailsResponse(order.getOrderId(), order.getProductId(), order.getTotalAmount(), order.getOrderDate(), order.getQuantity(), paymentInfo, productInfo);
    }
}