package dev.borriguel.paymentservice.service;

import dev.borriguel.paymentservice.model.entity.Payment;

public interface PaymentService {
    Payment doPayment(Payment payment);
    Payment getPaymentByOrderId(Long id);
}
