package se.lexicon.springboot.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String categoryName,
        String name,
        String description,
        BigDecimal price
) {
}
