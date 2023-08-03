package dev.borriguel.productservice.controller;

import dev.borriguel.productservice.model.dto.ProductRequest;
import dev.borriguel.productservice.model.dto.ProductResponse;
import dev.borriguel.productservice.service.ProductService;
import dev.borriguel.productservice.util.ProductMapper;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product", description = "Product API")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Product created", content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    public ProductResponse createProduct(@RequestBody @Valid ProductRequest request) {
        var product = service.saveProduct(mapper.toProduct(request));
        log.info("Product created: {}", product);
        return mapper.toProductResponse(product);
    }

    @PutMapping("/reduceQuantity/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Hidden
    public void reduceQuantity(@PathVariable(name = "id") Long productId, @RequestParam Long quantity) {
        service.reduceQuantity(productId, quantity);
        log.info("Product quantity reduced: {}", productId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "List of products", content = @Content(schema = @Schema(implementation = Page.class)))
    public Page<ProductResponse> getAllProducts(@ParameterObject Pageable page) {
        var products = service.getAllProducts(page);
        return products.map(mapper::toProductResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get product by ID")
    @ApiResponse(responseCode = "200", description = "Product found", content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    public ProductResponse getProductById(@PathVariable Long id) {
        var product = service.getProductById(id);
        log.info("Product found: {}", product);
        return mapper.toProductResponse(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete product by ID")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
        log.info("Product deleted: {}", id);
    }
}
