package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springboot.entity.Category;
import se.lexicon.springboot.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByCategoryNameIgnoreCase (String name);
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal low, BigDecimal high);

    List<Product> findByNameContainsIgnoreCase(String keyword);
    List<Product> findByPriceLessThan (BigDecimal price);
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
    long countByCategory_Name(String categoryName);
    List<Product> findByCategoryId (Long categoryId);
    boolean existsByNameIgnoreCaseAndCategory_NameIgnoreCase(String productName, String categoryName);
}
