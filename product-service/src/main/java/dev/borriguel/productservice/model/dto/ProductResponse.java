package dev.borriguel.productservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductResponse(@JsonProperty("product_name") String productName, BigDecimal price, @JsonProperty("product_id") Long productId, Long quantity) {
}
