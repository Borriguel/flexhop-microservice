package dev.borriguel.productservice.model.dto;

import java.time.Instant;
import java.util.List;

public record ValidationError(int statusCode, Instant timeStamp, List<String> message, String description) {
}
