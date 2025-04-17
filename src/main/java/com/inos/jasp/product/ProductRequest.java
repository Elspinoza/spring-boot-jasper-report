package com.inos.jasp.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductRequest {

    @NotEmpty(message = "Product name is mandatory")
    @NotBlank(message = "Product name is mandatory")
    @NotNull(message = "Product name is mandatory")
    private String name;

    @NotNull(message = "Product price is mandatory")
    @Min(value = 0, message = "Product price must be positive")
    private Double price;

    @NotNull(message = "Product quantity is mandatory")
    @Min(value = 1, message ="Product quantity cannot be inferior than 1")
    private Integer quantity;

    @NotNull(message = "To add product is mandatory to add his category")
    private UUID categoryId;
}
