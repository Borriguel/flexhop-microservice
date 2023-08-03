package dev.borriguel.paymentservice.client;

import dev.borriguel.paymentservice.exception.ExternalApiException;
import dev.borriguel.paymentservice.model.dto.OrderInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "order")
public interface OrderClient {
    @GetMapping("/order/{id}")
    @CircuitBreaker(name = "payment", fallbackMethod = "findOrderByIdFallback")
    @Retry(name = "payment", fallbackMethod = "findOrderByIdFallback")
    Optional<OrderInfo> findOrderById(@PathVariable(name = "id") Long orderId);
    default Optional<OrderInfo> findOrderByIdFallback(Exception ex) {
        throw new ExternalApiException("Order Service is unavailable");
    }
}
