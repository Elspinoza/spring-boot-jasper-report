package com.inos.jasp.category;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create")
    public ResponseEntity<String> create (
            @RequestBody @Valid CategoryRequest request
    ) {
        return ResponseEntity.ok(service.createCategory(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(service.fetchAllCategories());
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryResponse> getCategory(
            @PathVariable("category_id") UUID categoryId
    ) {
        return ResponseEntity.ok(service.getCategoryById(categoryId));
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable("category_id") UUID categoryId,
            @RequestBody CategoryRequest request
    ) {
        service.updateCategory(categoryId, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("category_id") UUID categoryId
    ) {
        service.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }


}
