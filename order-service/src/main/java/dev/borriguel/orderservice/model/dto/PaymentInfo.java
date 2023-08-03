package dev.borriguel.orderservice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentInfo(@JsonProperty("payment_id") Long paymentId, @JsonProperty("payment_date") Instant paymentDate,
                          @JsonProperty("payment_status") String paymentStatus,
                          @JsonProperty("payment_mode") String paymentMode) {
}
