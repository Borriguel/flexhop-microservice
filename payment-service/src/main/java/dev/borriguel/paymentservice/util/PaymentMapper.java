package dev.borriguel.paymentservice.util;

import dev.borriguel.paymentservice.model.dto.PaymentRequest;
import dev.borriguel.paymentservice.model.dto.PaymentResponse;
import dev.borriguel.paymentservice.model.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponse toPaymentResponse(Payment payment);
    Payment toPayment(PaymentRequest request);
}
