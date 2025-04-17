package com.inos.jasp.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CategoryResponse {

    private UUID id;
    private String name;

}
