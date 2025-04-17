package com.inos.jasp.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .build();
    }

    public CategoryResponse fromCategory(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
