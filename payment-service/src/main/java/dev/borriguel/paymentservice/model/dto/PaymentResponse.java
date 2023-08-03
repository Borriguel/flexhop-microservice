package dev.borriguel.paymentservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.borriguel.paymentservice.model.enums.PaymentMode;
import dev.borriguel.paymentservice.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(@JsonProperty("payment_id") Long paymentId,
                              @JsonProperty("order_id") Long orderId,
                              @JsonProperty("total_amount") BigDecimal totalAmount,
                              @JsonProperty("payment_date") Instant paymentDate,
                              Long quantity,
                              @JsonProperty("payment_status") PaymentStatus paymentStatus,
                              @JsonProperty("payment_mode") PaymentMode paymentMode) {
}
