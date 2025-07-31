package com.praneeth.ServiceTests;

import com.praneeth.Entity.Project;
import com.praneeth.Repository.ProjectRepository;
import com.praneeth.Service.Implementations.ProjectServiceImpl;
import com.praneeth.Service.Implementations.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepo;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void getAllProjects_shouldReturnListOfProjects() {
        List<Project> mockProjects = List.of(new Project(), new Project());
        when(projectRepo.findAll()).thenReturn(mockProjects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        verify(projectRepo).findAll();
    }
}
