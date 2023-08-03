package dev.borriguel.orderservice.client;

import dev.borriguel.orderservice.exception.BadRequestException;
import dev.borriguel.orderservice.exception.ExternalApiException;
import dev.borriguel.orderservice.exception.NotFoundException;
import dev.borriguel.orderservice.model.dto.ProductInfo;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/product/{id}")
    @CircuitBreaker(name = "order", fallbackMethod = "findProductByIdFallback")
    @Retry(name = "order", fallbackMethod = "findProductByIdFallback")
    Optional<ProductInfo> findProductById(@PathVariable(name = "id") Long productId);

    default Optional<ProductInfo> findProductByIdFallback(Exception ex) {
        throw new ExternalApiException("Product Service is unavailable");
    }

    @PutMapping("/product/reduceQuantity/{id}")
    @CircuitBreaker(name = "order", fallbackMethod = "reduceQuantityFallback")
    @Retry(name = "order", fallbackMethod = "reduceQuantityFallback")
    void reduceQuantity(@PathVariable(name = "id") Long productId, @RequestParam Long quantity);

    default void reduceQuantityFallback(FeignException ex) {
        if (ex.status() == 400) throw new BadRequestException("Insufficient quantity of product");
        if (ex.status() == 404) throw new NotFoundException("Product not found");
        else throw new ExternalApiException("Product Service is unavailable");
    }
}
