package com.inos.jasp.product;

import com.inos.jasp.category.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public String createProduct(ProductRequest request) {

        var product = repository.save(mapper.toProduct(request));

        return "Product created successfully" +product.getId();
    }


    public List<ProductResponse> fetchAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::fromProduct)
                .collect(Collectors.toList());
    }


    public ProductResponse getProductById(UUID productId) {
        return repository.findById(productId)
                .map(mapper::fromProduct)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with the ID:: "+productId));
    }


    public void updateProduct(UUID productId, ProductRequest request) {

        var product = repository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with the ID:: "+productId));

        mergeData(product, request);

        repository.save(product);
    }



    public void deleteProduct(UUID productId) {
        repository.deleteById(productId);
    }


    private void mergeData(Product product, ProductRequest request) {

        if (StringUtils.isNotBlank(request.getName())) {
            product.setName(request.getName());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        if (request.getCategoryId() != null) {

            product.setCategory(Category.builder()
                    .id(request.getCategoryId())
                    .build()
            );
        }

    }

}
