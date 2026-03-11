package se.lexicon.springboot.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull (message = "Product name is required")
        String name,
        String description,
        @NotNull (message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater then 0")
        Double price,
        Long categoryId
) {
}
