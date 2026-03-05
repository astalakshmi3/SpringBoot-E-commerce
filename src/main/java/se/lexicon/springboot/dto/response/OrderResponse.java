package se.lexicon.springboot.dto.response;

import se.lexicon.springboot.entity.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long Id,
        Long customerId,
        String status,
        List <OrderItemResponse> orderItems,
        BigDecimal total
) {
    public record OrderItemResponse(Long productId,
                                    String productName,
                                    Integer quantity,
                                    BigDecimal priceAtPurchase,
                                    BigDecimal totalPrice) {

    }
}