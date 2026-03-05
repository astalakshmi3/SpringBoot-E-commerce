package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    @Transactional
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse createProduct(ProductResponse productResponse);
    List<ProductResponse> findAllProducts();
    List<ProductResponse> findProductsByName(String name);
}
