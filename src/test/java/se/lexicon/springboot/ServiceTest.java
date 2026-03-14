package se.lexicon.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.entity.Product;
import se.lexicon.springboot.repository.CustomerRepository;
import se.lexicon.springboot.repository.ProductRepository;
import se.lexicon.springboot.service.CustomerService;
import se.lexicon.springboot.service.ProductService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(10.0));
    }
    @Test
    public void testSaveProduct() {
            when(productRepository.save(any(Product.class))).thenReturn(product);
            Product savedProduct = productService.create(Product product);
            assertNotNull(savedProduct);
            assertEquals("Test Product", savedProduct.getName());
            assertEquals(BigDecimal.valueOf(10.0), savedProduct.getPrice());
            verify(productRepository).save(any(Product.class));
    }
}
