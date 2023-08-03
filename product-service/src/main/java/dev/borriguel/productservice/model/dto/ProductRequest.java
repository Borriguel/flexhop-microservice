package dev.borriguel.productservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Invalid name: Empty name") @Size(max = 100, message = "Invalid name: Max lenght is 100 characters") @JsonProperty("product_name") String productName,
        @DecimalMin(value = "0", message = "Invalid price: Negative value") BigDecimal price,
        @Min(value = 1, message = "Invalid quantity: Equals to zero or less than zero") Long quantity) {
}
