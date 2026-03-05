package se.lexicon.springboot.service;

import se.lexicon.springboot.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductResponse productResponse);
    List<ProductResponse> findAllProducts();
    List<ProductResponse> findProductsByName(String name);
}
