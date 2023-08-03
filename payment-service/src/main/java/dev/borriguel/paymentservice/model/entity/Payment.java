package dev.borriguel.paymentservice.model.entity;

import dev.borriguel.paymentservice.model.enums.PaymentMode;
import dev.borriguel.paymentservice.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(unique = true)
    private Long orderId;
    @CreationTimestamp
    private Instant paymentDate;
    @Column(nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @Column(nullable = false)
    private Long quantity;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (!paymentId.equals(payment.paymentId)) return false;
        if (!orderId.equals(payment.orderId)) return false;
        if (!paymentDate.equals(payment.paymentDate)) return false;
        if (!totalAmount.equals(payment.totalAmount)) return false;
        if (!quantity.equals(payment.quantity)) return false;
        if (paymentMode != payment.paymentMode) return false;
        return paymentStatus == payment.paymentStatus;
    }

    @Override
    public int hashCode() {
        int result = paymentId.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + paymentDate.hashCode();
        result = 31 * result + totalAmount.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + paymentMode.hashCode();
        result = 31 * result + paymentStatus.hashCode();
        return result;
    }


}
