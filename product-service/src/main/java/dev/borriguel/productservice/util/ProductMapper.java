package dev.borriguel.productservice.util;

import dev.borriguel.productservice.model.dto.ProductRequest;
import dev.borriguel.productservice.model.dto.ProductResponse;
import dev.borriguel.productservice.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductRequest request);
}
