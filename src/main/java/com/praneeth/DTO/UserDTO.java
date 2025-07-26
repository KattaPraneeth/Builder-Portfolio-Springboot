package com.praneeth.DTO;

import com.praneeth.Enum.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private UserRole role;
}
