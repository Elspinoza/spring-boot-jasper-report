package com.inos.jasp.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryRequest {

    @NotEmpty(message = "Category name is mandatory")
    @NotBlank(message = "Category name is mandatory")
    @NotNull(message = "Category name is mandatory")
    private String name;
}
