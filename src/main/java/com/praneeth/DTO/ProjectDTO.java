package com.praneeth.DTO;

import com.praneeth.Enum.ProjectStatus;
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
}
