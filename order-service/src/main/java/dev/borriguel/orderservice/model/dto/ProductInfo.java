package dev.borriguel.orderservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductInfo(@JsonProperty("product_name") String productName, BigDecimal price,
                          @JsonProperty("product_id") Long productId) {
}