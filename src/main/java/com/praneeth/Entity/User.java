package com.praneeth.Entity;

import com.praneeth.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Project> clientProjects;

    @OneToMany(mappedBy = "builder", cascade = CascadeType.ALL)
    private List<Project> builderProjects;

    public User() {}

    public User(Long id, String name, String email, UserRole role, List<Project> clientProjects, List<Project> builderProjects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.clientProjects = clientProjects;
        this.builderProjects = builderProjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Project> getClientProjects() {
        return clientProjects;
    }

    public void setClientProjects(List<Project> clientProjects) {
        this.clientProjects = clientProjects;
    }

    public List<Project> getBuilderProjects() {
        return builderProjects;
    }

    public void setBuilderProjects(List<Project> builderProjects) {
        this.builderProjects = builderProjects;
    }
}
