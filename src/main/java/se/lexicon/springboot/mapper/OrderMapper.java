package se.lexicon.springboot.mapper;

import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.response.OrderResponse;
import se.lexicon.springboot.entity.Order;
import se.lexicon.springboot.entity.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");

        List<OrderItemResponse> itemResponses = order.getItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        BigDecimal total = itemResponses.stream()
                .map(OrderItemResponse::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getStatus().name(),
                order.getCreatedAt(),
                itemResponses,
                total
        );
    }

    public OrderItemResponse toItemResponse(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("OrderItem cannot be null");

        BigDecimal totalPrice = item.getProduct().getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity()));

        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPriceAtPurchase(),
                totalPrice
        );
    }
}

