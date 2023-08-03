package dev.borriguel.orderservice.model.entity;

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
@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    @CreationTimestamp
    private Instant orderDate;
    @Column(nullable = false)
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!orderId.equals(order.orderId)) return false;
        if (!productId.equals(order.productId)) return false;
        if (!totalAmount.equals(order.totalAmount)) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        return quantity.equals(order.quantity);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + productId.hashCode();
        result = 31 * result + totalAmount.hashCode();
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
