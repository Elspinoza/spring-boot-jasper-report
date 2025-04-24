package com.inos.jasp.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public String createCategory(CategoryRequest request) {

        try {

            var saved = repository.save(mapper.toCategory(request));

            return "Category "+saved.getName()+" created successfully.";

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public List<CategoryResponse> fetchAllCategories() {

        return repository.findAll()
                .stream()
                .map(mapper::fromCategory)
                .collect(Collectors.toList());
    }


    public CategoryResponse getCategoryById(UUID categoryId) {

        return repository.findById(categoryId)
                .map(mapper::fromCategory)
                .orElseThrow(()-> new EntityNotFoundException("Category not found with the ID:: "+categoryId));
    }


    @Transactional
    public void updateCategory(UUID categoryId, CategoryRequest request) {

        try {

            var category = repository.findById(categoryId)
                    .orElseThrow(()-> new EntityNotFoundException("Category not found with the ID:: "+categoryId));

            mergeData(category, request);

            repository.save(category);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(UUID categoryId) {
        repository.deleteById(categoryId);
    }

    private void mergeData(Category category, CategoryRequest request) {

        if (StringUtils.isNotBlank(request.getName())){
            category.setName(request.getName());
        }
    }
}
