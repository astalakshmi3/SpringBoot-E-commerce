package se.lexicon.springboot.service;

import jakarta.transaction.Transactional;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    @Transactional
    ProductResponse create(ProductRequest productRequest);

    ProductResponse create(ProductResponse productResponse);
    List<ProductResponse> findAll();
    List<ProductResponse> findByName(String name);
}
