package com.praneeth.Validators;

import com.praneeth.DTO.ProjectDTO;
import com.praneeth.DTO.UserDTO;
import com.praneeth.Enum.ProjectStatus;
import com.praneeth.Enum.UserRole;
import com.praneeth.Exceptions.InvalidProjectDataException;
import com.praneeth.Exceptions.InvalidUserDataException;

public class Validator {
    public static void validateUserDTO(UserDTO dto) {
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

        if (dto.getRole() != UserRole.CLIENT && dto.getRole() != UserRole.BUILDER && dto.getRole() != UserRole.ADMIN) {
            throw new InvalidUserDataException("Invalid user role: " + dto.getRole());
        }
    }

    public static void validateProjectDTO(ProjectDTO dto) {
        if (dto == null) {
            throw new InvalidProjectDataException("Project data cannot be null");
        }

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new InvalidProjectDataException("Title is missing or blank");
        }

        if (dto.getStatus() == null) {
            throw new InvalidProjectDataException("Project status is missing");
        }

        if (dto.getStatus() != ProjectStatus.UPCOMING && dto.getStatus() != ProjectStatus.IN_PROGRESS && dto.getStatus() != ProjectStatus.COMPLETED) {
            throw new InvalidProjectDataException("Invalid project status");
        }

        if (dto.getClientId() == null) {
            throw new InvalidProjectDataException("Client ID is missing");
        }

        if (dto.getBuilderId() == null) {
            throw new InvalidProjectDataException("Builder ID is missing");
        }
    }
}
