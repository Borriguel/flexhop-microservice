package dev.borriguel.productservice.service.impl;

import dev.borriguel.productservice.exception.BadRequestException;
import dev.borriguel.productservice.exception.NotFoundException;
import dev.borriguel.productservice.model.entity.Product;
import dev.borriguel.productservice.repository.ProductRepository;
import dev.borriguel.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        if (repository.existsByProductNameIgnoreCase(product.getProductName()))
            throw new BadRequestException("Product already exists");
        return repository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        getProductById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    @Transactional
    public void reduceQuantity(Long id, Long quantity) {
        log.info("Reducing quantity of product {} by {}", id, quantity);
        var product = getProductById(id);
        log.info("Product found: {}", product);
        if (quantity > product.getQuantity()) throw new BadRequestException("Product insufficient quantity");
        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
    }
}
