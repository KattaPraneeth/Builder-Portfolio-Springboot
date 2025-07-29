package com.praneeth.DTO;

import com.praneeth.Enum.UserRole;
import com.praneeth.Exceptions.InvalidUserDataException;
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

    public UserDTO() {}

    public UserDTO(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static void validate(UserDTO dto) {
        if (dto == null) {
            throw new InvalidUserDataException("User data cannot be null");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new InvalidUserDataException("Name is missing or blank");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new InvalidUserDataException("Email is missing or blank");
        }

        if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidUserDataException("Email format is invalid");
        }

        if (dto.getRole() == null) {
            throw new InvalidUserDataException("User role is missing");
        }
    }
}
