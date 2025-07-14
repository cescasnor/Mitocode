package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mitocode.model.Client;
import com.mitocode.model.Role;
import com.mitocode.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //Hace que en la salida del JSON no se muestre los campos con NULOS {cliente : null X }
public class SaleDTO {

    private Integer idSale;

    @NotNull
    private Client client;

    @NotNull
    private User user;

    @NotNull
    private LocalDateTime datetime;

    @Min(value = 1)
    private double total;

    @Min(value = 1)
    private double tax;

    @NotNull
    private boolean enabled;

    @NotNull
    @JsonManagedReference
    private List<SaleDetailDTO> details;

}
