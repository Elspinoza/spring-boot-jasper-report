package com.inos.jasp.product;

import com.inos.jasp.excel.ExcelGenerator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportProduitsExcel() throws IOException {
        List<Produit> produits = List.of(
                new Produit(1L, "Produit A", "Description A", 10, true),
                new Produit(2L, "Produit B", "Description B", 20, false),
                new Produit(5L, "Produit B", "Description B", 30, true),
                new Produit(6L, "Produit B", "Description B", 40, false),
                new Produit(7L, "Produit B", "Description B", 50, true),
                new Produit(8L, "Produit B", "Description B", 60, false)
        );

        byte[] excelData = ExcelGenerator.generateExcel(produits);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produits.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }


}
