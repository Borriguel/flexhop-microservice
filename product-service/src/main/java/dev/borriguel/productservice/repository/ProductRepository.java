package dev.borriguel.productservice.repository;

import dev.borriguel.productservice.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductNameIgnoreCase(String productName);
}
