package se.lexicon.springboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
            @NotNull
            Long customerId,
            @NotEmpty
            List<OrderItemRequest> orderItems
) {
    public record OrderItemRequest(
            @NotNull
            Long productId,
            @Min(value = 1, message = "Quantity must be at least 1")
            Integer quantity
    ) {
    }
}
