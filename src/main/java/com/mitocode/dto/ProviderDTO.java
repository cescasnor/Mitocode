package com.mitocode.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //Hace que en la salida del JSON no se muestre los campos con NULOS {cliente : null X }
public class ProviderDTO {


    private Integer idProvider;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String nameProvider;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String addressProvider;

    @NotNull
    private boolean enableProvider;
}
