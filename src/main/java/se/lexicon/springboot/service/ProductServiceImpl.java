package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;
import se.lexicon.springboot.entity.Category;
import se.lexicon.springboot.entity.Product;
import se.lexicon.springboot.mapper.ProductMapper;
import se.lexicon.springboot.repository.CategoryRepository;
import se.lexicon.springboot.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;

    }


    @Override
    @Transactional
    public ProductResponse create(ProductRequest productRequest) {
        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + productRequest.categoryId()));

    Product product = productMapper.toEntity(productRequest, category);
    Product savedProduct = productRepository.save(product);
    return ProductMapper.toProductResponse(savedProduct);
     }

    @Override
    public ProductResponse create(ProductResponse productResponse) {
        return null;
    }


    @Override
    @Transactional
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductResponse)
                .toList();
}

    @Override
    @Transactional
    public List<ProductResponse> findByName(String name) {
        return productRepository.findByCategoryNameIgnoreCase(name).stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }
}
