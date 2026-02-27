package se.lexicon.springboot.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import se.lexicon.springboot.entity.Category;
import se.lexicon.springboot.entity.Product;
import se.lexicon.springboot.entity.Promotion;
import se.lexicon.springboot.repository.CategoryRepository;
import se.lexicon.springboot.repository.ProductRepository;
import se.lexicon.springboot.repository.PromotionRepository;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PromotionRepository promotionRepository;

    @Autowired
    public MyCommandLineRunner(ProductRepository productRepository, CategoryRepository categoryRepository, PromotionRepository promotionRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.promotionRepository = promotionRepository;
    }


    @Override
    public void run(String... args) throws Exception {
            // Check if data already exists to avoid duplication
            if (categoryRepository.count() > 0) {
                System.out.println("Data already exists, skipping initialization.");
                return;
            }
            // Create sample categories
            Category electronics = new Category();
            electronics.setName("Electronics");
            categoryRepository.save(electronics);
            Category books = new Category();
            books.setName("Books");
            categoryRepository.save(books);


            // Create sample products
            Product product = new Product();
            product.setName("Smartphone");
            product.setPrice(BigDecimal.valueOf(499.99));
            product.setCategory(electronics);
          //  product.setPromotions(Set.of (winterSale));
            productRepository.save(product);

            Product product1 = new Product();
            product1.setName("Laptop");
            product1.setPrice(BigDecimal.valueOf(999.99));
            product1.setCategory(electronics);
          //  product1.setPromotions(Set.of (winterSale));
            productRepository.save(product1);

            productRepository.saveAll(List.of(product, product1));
        System.out.println("Data initialization completed.");
        }
    }