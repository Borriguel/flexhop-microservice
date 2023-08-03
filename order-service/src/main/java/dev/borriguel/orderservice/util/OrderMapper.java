package dev.borriguel.orderservice.util;

import dev.borriguel.orderservice.model.dto.OrderRequest;
import dev.borriguel.orderservice.model.dto.OrderResponse;
import dev.borriguel.orderservice.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    Order toOrder(OrderRequest orderRequest);
}
