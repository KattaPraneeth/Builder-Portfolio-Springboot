package com.praneeth.Service.Interfaces;

import com.praneeth.DTO.ProjectDTO;
import com.praneeth.Entity.Project;

import java.util.List;

public interface ProjectService {
    Project addProject(ProjectDTO dto);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    Project updateProject(Long id, ProjectDTO dto);
    void deleteProject(Long id);
}
