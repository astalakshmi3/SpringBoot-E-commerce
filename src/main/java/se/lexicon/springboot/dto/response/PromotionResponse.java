package se.lexicon.springboot.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromotionResponse(
        Long id,
        String name,
        BigDecimal discountPercentage,
        LocalDate startDate,
        LocalDate endDate,
        boolean active
) {
}
