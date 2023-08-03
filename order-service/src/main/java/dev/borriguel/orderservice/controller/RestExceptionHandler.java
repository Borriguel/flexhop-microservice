package dev.borriguel.orderservice.controller;

import dev.borriguel.orderservice.exception.BadRequestException;
import dev.borriguel.orderservice.exception.ExternalApiException;
import dev.borriguel.orderservice.exception.NotFoundException;
import dev.borriguel.orderservice.model.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(NotFoundException exception, WebRequest request) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), Instant.now(), exception.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(ExternalApiException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiError handleExternalApiException(ExternalApiException exception, WebRequest request) {
        return new ApiError(HttpStatus.SERVICE_UNAVAILABLE.value(), Instant.now(), exception.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(BadRequestException exception, WebRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), Instant.now(), exception.getMessage(), request.getDescription(false));
    }
}
