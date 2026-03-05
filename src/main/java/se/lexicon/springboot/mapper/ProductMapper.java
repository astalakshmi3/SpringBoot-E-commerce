package se.lexicon.springboot.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;
import se.lexicon.springboot.entity.Category;
import se.lexicon.springboot.entity.Product;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public static ProductResponse toProductResponse (Product product){
        if (product == null) throw new IllegalArgumentException("Product cannot be null");

        Category category = product.getCategory();
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                category != null ? category.getName() : null);
    }

    public Product toEntity (ProductRequest request, Category category){
        if (request == null) return null;

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(BigDecimal.valueOf(request.price()));
        product.setCategory(category);
        return product;
    }
}
