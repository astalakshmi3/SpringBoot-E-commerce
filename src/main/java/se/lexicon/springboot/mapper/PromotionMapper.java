package se.lexicon.springboot.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.request.PromotionRequest;
import se.lexicon.springboot.dto.response.CategoryResponse;
import se.lexicon.springboot.dto.response.PromotionResponse;
import se.lexicon.springboot.entity.Promotion;

@Component
public class PromotionMapper {
    private PromotionMapper() {}

    //Request to Entity
    public Promotion toEntity(PromotionRequest promotionRequest) {
        if (promotionRequest == null) return null;

        Promotion promotion = new Promotion();
        promotion.setName(promotionRequest.name());
        promotion.setDiscountPercentage(promotionRequest.discountPercentage());
        promotion.setStartDate(promotionRequest.startDate());
        promotion.setEndDate(promotionRequest.endDate());
        promotion.setActive(promotionRequest.active());
        return promotion;
    }

    public PromotionResponse toResponse(Promotion promotion) {
        if (promotion == null) return null;

        return new PromotionResponse(
                promotion.getId(),
                promotion.getName(),
                promotion.getDiscountPercentage(),
                promotion.getStartDate(),
                promotion.getEndDate(),
                promotion.isActive()
        );
    }
}

