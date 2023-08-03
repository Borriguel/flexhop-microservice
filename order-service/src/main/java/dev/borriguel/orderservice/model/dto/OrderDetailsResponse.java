package dev.borriguel.orderservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderDetailsResponse(@JsonProperty("order_id") Long orderId, @JsonProperty("product_id") Long productId,
                                   @JsonProperty("total_amount") BigDecimal totalAmount,
                                   @JsonProperty("order_date") Instant orderDate, Long quantity,
                                   @JsonProperty("payment_info") PaymentInfo paymentInfo,
                                   @JsonProperty("product_info") ProductInfo productInfo) {
}
