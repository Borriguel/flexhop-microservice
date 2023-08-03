package dev.borriguel.orderservice.model.dto;

import java.time.Instant;

public record ApiError(int statusCode, Instant timeStamp, String message, String description) {
}

