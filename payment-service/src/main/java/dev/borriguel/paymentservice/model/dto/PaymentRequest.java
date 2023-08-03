package dev.borriguel.paymentservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.borriguel.paymentservice.model.enums.PaymentMode;

public record PaymentRequest(@JsonProperty("order_id") Long orderId,
                             @JsonProperty("payment_mode") PaymentMode paymentMode) {
}
