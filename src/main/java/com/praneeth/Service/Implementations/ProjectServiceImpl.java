package com.praneeth.Service.Implementations;

import com.praneeth.DTO.ProjectDTO;
import com.praneeth.Entity.Project;
import com.praneeth.Entity.User;
import com.praneeth.Enum.UserRole;
import com.praneeth.Exceptions.BuilderNotFoundException;
import com.praneeth.Exceptions.ClientNotFoundException;
import com.praneeth.Exceptions.ProjectNotFoundException;
import com.praneeth.Exceptions.ResourceNotFoundException;
import com.praneeth.Repository.ProjectRepository;
import com.praneeth.Service.Interfaces.ProjectService;
import com.praneeth.Service.Interfaces.UserService;
import com.praneeth.Validators.Validator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private UserService userService;
    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Override
    public Project addProject(ProjectDTO dto) {
        Validator.validateProjectDTO(dto);
        User client = null;
        User builder = null;

        try {
            client = userService.getUserById(dto.getClientId());
            if (!(client.getRole() == UserRole.CLIENT)) {
                throw new ClientNotFoundException("User with ID " + dto.getClientId() + " is not a client.");
            }
        } catch (ResourceNotFoundException e) {
            throw new ClientNotFoundException("Client not found with ID: " + dto.getClientId());
        }

        try {
            builder = userService.getUserById(dto.getBuilderId());
            if (!(builder.getRole() == UserRole.BUILDER)) {
                throw new BuilderNotFoundException("User with ID " + dto.getBuilderId() + " is not a builder.");
            }
        } catch (ResourceNotFoundException e) {
            throw new BuilderNotFoundException("Builder not found with ID: " + dto.getBuilderId());
        }

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setStatus(dto.getStatus());
        project.setClient(client);
        project.setBuilder(builder);

        log.info("Creating project: {}", dto.getTitle());
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
    }

    @Override
    public Project updateProject(Long id, ProjectDTO dto) {
        Validator.validateProjectDTO(dto);
        Project project = getProjectById(id);

        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setStatus(dto.getStatus());

        try {
            User client = userService.getUserById(dto.getClientId());
            if (!(client.getRole() == UserRole.CLIENT)) {
                throw new ClientNotFoundException("User with ID " + dto.getClientId() + " is not a client.");
            }
            project.setClient(client);
        } catch (ResourceNotFoundException e) {
            throw new ClientNotFoundException("Client not found with ID: " + dto.getClientId());
        }

        try {
            User builder = userService.getUserById(dto.getBuilderId());
            if (!(builder.getRole() == UserRole.BUILDER)) {
                throw new BuilderNotFoundException("User with ID " + dto.getBuilderId() + " is not a builder.");
            }
            project.setBuilder(builder);
        } catch (ResourceNotFoundException e) {
            throw new BuilderNotFoundException("Builder not found with ID: " + dto.getBuilderId());
        }

        log.info("Updating project with ID: {}", id);
        return projectRepo.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepo.existsById(id)) {
            throw new ProjectNotFoundException("Project not found");
        }
        projectRepo.deleteById(id);
        log.info("Deleted project with ID: {}", id);
    }
}