package dev.borriguel.productservice.service;

import dev.borriguel.productservice.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Page<Product> getAllProducts(Pageable page);
    void reduceQuantity(Long id, Long quantity);
}
