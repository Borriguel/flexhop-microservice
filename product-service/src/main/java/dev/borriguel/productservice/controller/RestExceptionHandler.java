package dev.borriguel.productservice.controller;

import dev.borriguel.productservice.exception.BadRequestException;
import dev.borriguel.productservice.exception.NotFoundException;
import dev.borriguel.productservice.model.dto.ApiError;
import dev.borriguel.productservice.model.dto.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(NotFoundException exception, WebRequest request) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), Instant.now(), exception.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBadRequestException(BadRequestException exception, WebRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), Instant.now(), exception.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        List<String> listMessages = new ArrayList<>();
        for (FieldError f : exception.getBindingResult().getFieldErrors()) {
            listMessages.add(f.getField() + ": " + f.getDefaultMessage());
        }
        return new ValidationError(HttpStatus.BAD_REQUEST.value(), Instant.now(), listMessages, request.getDescription(false));
    }
}
