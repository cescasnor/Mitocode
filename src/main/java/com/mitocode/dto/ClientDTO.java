package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //Hace que en la salida del JSON no se muestre los campos con NULOS {cliente : null X }
public class ClientDTO {

    private Integer idClient;

    @NotNull
    @NotBlank // Tambien se puede utilizar @NotEmpty
    @Size(min=1,max=20)
    private String firstName;

    private String lastName;
    private String country;

    @NotNull
    @NotEmpty
    @Size(min=10,max=20)
    private String cardId;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Email
    private String email;
    private String address;

    @Max(value = 99)
    @Min(value = 1)
    private int age;

}
