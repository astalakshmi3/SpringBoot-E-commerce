package se.lexicon.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;
import se.lexicon.springboot.entity.Product;
import se.lexicon.springboot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produts")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse createdProduct = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductsByName(name));
    }
}
