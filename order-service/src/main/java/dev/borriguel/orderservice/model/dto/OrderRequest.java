package dev.borriguel.orderservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequest(@JsonProperty("product_id") Long productId, Long quantity) {
}
