package com.inos.jasp.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Produit {

    private Long id;
    private String nom;
    private String description;
    private int quantite;
    private boolean enStock;
}
