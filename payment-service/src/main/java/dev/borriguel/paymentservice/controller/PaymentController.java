package dev.borriguel.paymentservice.controller;

import dev.borriguel.paymentservice.model.dto.PaymentRequest;
import dev.borriguel.paymentservice.model.dto.PaymentResponse;
import dev.borriguel.paymentservice.service.PaymentService;
import dev.borriguel.paymentservice.util.PaymentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Payment", description = "Payment API")
public class PaymentController {
    private final PaymentService service;
    private final PaymentMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Process a payment")
    @ApiResponse(responseCode = "201", description = "Payment created", content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    public PaymentResponse doPayment(@RequestBody PaymentRequest paymentRequest) {
        var payment = service.doPayment(mapper.toPayment(paymentRequest));
        log.info("Payment created: {}", payment);
        return mapper.toPaymentResponse(payment);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get payment by order ID")
    @ApiResponse(responseCode = "200", description = "Payment found", content = @Content(schema = @Schema(implementation = PaymentResponse.class)))
    public PaymentResponse getPaymentByOrderId(@PathVariable Long id) {
        var payment = service.getPaymentByOrderId(id);
        log.info("Payment found: {}", payment);
        return mapper.toPaymentResponse(payment);
    }
}
