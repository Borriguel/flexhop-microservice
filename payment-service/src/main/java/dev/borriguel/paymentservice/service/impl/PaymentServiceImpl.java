package dev.borriguel.paymentservice.service.impl;

import dev.borriguel.paymentservice.client.OrderClient;
import dev.borriguel.paymentservice.exception.BadRequestException;
import dev.borriguel.paymentservice.exception.NotFoundException;
import dev.borriguel.paymentservice.model.entity.Payment;
import dev.borriguel.paymentservice.model.enums.PaymentStatus;
import dev.borriguel.paymentservice.repository.PaymentRepository;
import dev.borriguel.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final OrderClient orderClient;

    @Override
    @Transactional
    public Payment doPayment(Payment payment) {
        if (repository.existsByOrderId(payment.getOrderId())) throw new BadRequestException("Payment already done");
        var order = orderClient.findOrderById(payment.getOrderId()).orElseThrow(() -> new NotFoundException("Order not found"));
        log.info("Order found: {}", order);
        payment.setTotalAmount(order.totalAmount());
        payment.setQuantity(order.quantity());
        payment.setPaymentStatus(PaymentStatus.APPROVED);
        payment.setPaymentDate(Instant.now());
        repository.save(payment);
        return payment;
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByOrderId(Long orderId) {
        var payment = repository.findByOrderId(orderId);
        return payment.orElseGet(() -> new Payment(null, null, null, null, null, null, PaymentStatus.PENDING));
    }
}
