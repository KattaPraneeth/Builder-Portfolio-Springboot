package com.praneeth.DTO;

import com.praneeth.Enum.ProjectStatus;
import com.praneeth.Exceptions.InvalidProjectDataException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectDTO {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private ProjectStatus status;

    @NotNull
    private Long clientId;

    @NotNull
    private Long builderId;

    public ProjectDTO() {}

    public ProjectDTO(String title, String description, ProjectStatus status, Long clientId, Long builderId) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.clientId = clientId;
        this.builderId = builderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBuilderId() {
        return builderId;
    }

    public void setBuilderId(Long builderId) {
        this.builderId = builderId;
    }

    public static void validate(ProjectDTO dto) {
        if (dto == null) {
            throw new InvalidProjectDataException("Project data cannot be null");
        }

        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new InvalidProjectDataException("Title is missing or blank");
        }

        if (dto.getStatus() == null) {
            throw new InvalidProjectDataException("Project status is missing");
        }

        if (dto.getClientId() == null) {
            throw new InvalidProjectDataException("Client ID is missing");
        }

        if (dto.getBuilderId() == null) {
            throw new InvalidProjectDataException("Builder ID is missing");
        }
    }
}
