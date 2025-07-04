package com.mitocode.dto;

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
public class UserDTO {

    private Integer idUser;

    @NotNull
    private Role role;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 60)
    private String password;

    @NotNull
    private boolean enabled;
}
