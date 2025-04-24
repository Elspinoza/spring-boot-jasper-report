package com.inos.jasp.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuantityRequest {

    @NotNull(message = "The quantity is mandatory")
    @Min(value = 1, message = "The minimum to add is 1")
    @Positive(message = "The minimum to add is 1, not a negative number")
    private Integer quantity;
}
