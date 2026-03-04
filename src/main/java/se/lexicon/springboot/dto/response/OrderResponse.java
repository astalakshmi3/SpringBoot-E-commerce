package se.lexicon.springboot.dto.response;

import se.lexicon.springboot.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long productId,
        String productName,
        int quantity,
        String status,
        List <OrderItemResponse> orderItems
) {
    public record  OrderItemResponse(Long id, Long customerId, String status, LocalDateTime createdId) {}
}
