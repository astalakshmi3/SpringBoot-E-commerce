package se.lexicon.springboot.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.response.OrderResponse;
import se.lexicon.springboot.entity.Order;
import se.lexicon.springboot.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null");

        List<OrderResponse.OrderItemResponse> itemResponses = order.getItems().stream()
                .map(this::toItemResponse)
                .toList();

        BigDecimal total = itemResponses.stream()
                .map(OrderResponse.OrderItemResponse::totalPrice) // record accessor
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getStatus().name(),
                itemResponses,
                total
        );
    }

    private OrderResponse.OrderItemResponse toItemResponse(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("OrderItem cannot be null");

        BigDecimal totalPrice = item.getPriceAtPurchase()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        return new OrderResponse.OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPriceAtPurchase(),
                totalPrice
        );
    }
}