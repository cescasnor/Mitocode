package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mitocode.model.Role;
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
public class UserDTO {

    private Integer idUser;

    @JsonIncludeProperties(value = {"idRole","nameRole"})
    @NotNull
    private Role role;

    @JsonProperty(value = "user_name")
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    //@JsonIgnore -> Este ignora tanto de entrada como salida, por lo cual se tiene que tener cuidado
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 60)
    private String password;

    @NotNull
    private boolean enabled;
}
