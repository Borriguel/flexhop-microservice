package dev.borriguel.productservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true, nullable = false, length = 100)
    private String productName;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!productId.equals(product.productId)) return false;
        if (!productName.equals(product.productName)) return false;
        if (!price.equals(product.price)) return false;
        return quantity.equals(product.quantity);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
