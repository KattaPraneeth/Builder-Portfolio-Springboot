package com.praneeth.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praneeth.Enum.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonManagedReference("client-project")
    private User client;

    @ManyToOne
    @JoinColumn(name = "builder_id")
    @JsonManagedReference("builder-project")
    private User builder;

    public Project() {}

    public Project(Long id, String title, String description, ProjectStatus status, User client, User builder) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.client = client;
        this.builder = builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
    }
}
