package se.lexicon.springboot.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springboot.dto.request.ProductRequest;
import se.lexicon.springboot.dto.response.ProductResponse;
import se.lexicon.springboot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest productRequest) {
        ProductResponse created = productService.create(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> findByName(
            @RequestParam String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }
}