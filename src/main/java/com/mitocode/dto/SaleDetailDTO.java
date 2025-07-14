package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //Hace que en la salida del JSON no se muestre los campos con NULOS {cliente : null X }
public class SaleDetailDTO {

    @JsonBackReference
    private SaleDTO sale;

    @NotNull
    private ProductDTO product;

    @Min(value = 1)
    private short quantity;

    @Min(value = 1)
    private double salePrice;

    @Min(value = 0)
    private double discount;

}
