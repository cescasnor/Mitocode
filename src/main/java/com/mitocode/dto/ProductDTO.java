package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer idProduct;
    private Integer idCategoria;
    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;
    private boolean enabledProduct;

}
