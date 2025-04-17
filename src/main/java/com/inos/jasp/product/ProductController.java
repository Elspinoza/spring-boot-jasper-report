package com.inos.jasp.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<String> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(service.fetchAllProducts());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("product_id") UUID productId
    ) {
        return ResponseEntity.ok(service.getProductById(productId));
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("product_id") UUID productId,
            @RequestBody ProductRequest request
    ) {
        service.updateProduct(productId, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("product_id") UUID productId
    ) {
        service.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
