package dev.borriguel.orderservice.client;

import dev.borriguel.orderservice.model.dto.PaymentInfo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment")
public interface PaymentClient {
    @GetMapping("/payment/{id}")
    @CircuitBreaker(name = "order", fallbackMethod = "getPaymentByOrderIdFallback")
    @Retry(name = "order", fallbackMethod = "getPaymentByOrderIdFallback")
    PaymentInfo getPaymentByOrderId(@PathVariable(name = "id") Long orderId);
    default PaymentInfo getPaymentByOrderIdFallback(Exception ex) {
        return new PaymentInfo(null, null, "Payment service unavailable", "Payment service unavailable");
    }
}