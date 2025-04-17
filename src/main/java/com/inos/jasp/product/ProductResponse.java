package com.inos.jasp.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Builder
public class ProductResponse {

    private UUID id;
    private String name;
    private Double price;
    private Integer quantity;
    private UUID categoryId;
    private String categoryName;
}
