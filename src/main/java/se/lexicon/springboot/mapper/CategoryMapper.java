package se.lexicon.springboot.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.request.CategoryRequest;
import se.lexicon.springboot.dto.response.CategoryResponse;
import se.lexicon.springboot.entity.Category;

@Component
public class CategoryMapper {
   public CategoryResponse toCategoryResponse(Category category) {
       if (category == null) throw new IllegalArgumentException("category is null");
       return new CategoryResponse(
               category.getId(),
               category.getName()
       );
   }

    //Request to Entity
    public static Category toEntity(CategoryRequest categoryRequest) {
        if  (categoryRequest == null) throw new IllegalArgumentException("categoryRequest cannot be null");

        Category category = new Category();
        category.setName(categoryRequest.name());
        return category;
    }

    //Entity to Response
    public static CategoryResponse toResponse(Category category) {
        if  (category == null) return null;

        return new CategoryResponse(category.getId(), category.getName());
    }
}

