package se.lexicon.springboot.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromotionRequest(String name,
        BigDecimal discountPercentage,
        LocalDate startDate,
        LocalDate endDate,
        boolean active) {
}
