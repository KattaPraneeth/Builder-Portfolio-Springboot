package com.praneeth.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praneeth.Controller.ProjectController;
import com.praneeth.DTO.ProjectDTO;
import com.praneeth.Entity.Project;
import com.praneeth.Enum.ProjectStatus;
import com.praneeth.Exceptions.BuilderNotFoundException;
import com.praneeth.Exceptions.ClientNotFoundException;
import com.praneeth.Exceptions.InvalidProjectDataException;
import com.praneeth.Service.Interfaces.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    private final ObjectMapper mapper = new ObjectMapper();

    private ProjectDTO getValidDTO() {
        ProjectDTO dto = new ProjectDTO();
        dto.setTitle("New Tower");
        dto.setDescription("20 floors");
        dto.setStatus(ProjectStatus.UPCOMING);
        dto.setClientId(1L);
        dto.setBuilderId(2L);
        return dto;
    }

    @Test
    void createProject_shouldReturn201() throws Exception {
        ProjectDTO dto = getValidDTO();

        Project created = new Project();
        created.setId(100L);
        created.setTitle(dto.getTitle());
        created.setStatus(dto.getStatus());

        when(projectService.addProject(any(ProjectDTO.class))).thenReturn(created);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createProject_whenClientNotFound_shouldReturn404() throws Exception {
        ProjectDTO dto = getValidDTO();

        when(projectService.addProject(any(ProjectDTO.class)))
                .thenThrow(new ClientNotFoundException("Client not found with ID: " + dto.getClientId()));

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Client not found with ID: " + dto.getClientId()));
    }

    @Test
    void createProject_whenBuilderNotFound_shouldReturn404() throws Exception {
        ProjectDTO dto = getValidDTO();

        when(projectService.addProject(any(ProjectDTO.class)))
                .thenThrow(new BuilderNotFoundException("Builder not found with ID: " + dto.getBuilderId()));

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Builder not found with ID: " + dto.getBuilderId()));
    }

    @Test
    void createProject_whenInvalidProjectData_shouldReturn400() throws Exception {
        ProjectDTO dto = getValidDTO();
        dto.setTitle("");

        when(projectService.addProject(any(ProjectDTO.class)))
                .thenThrow(new InvalidProjectDataException("Title cannot be empty"));

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Title cannot be empty"));
    }

    @Test
    void createProject_whenUnexpectedError_shouldReturn500() throws Exception {
        ProjectDTO dto = getValidDTO();

        when(projectService.addProject(any(ProjectDTO.class)))
                .thenThrow(new RuntimeException("Database connection error"));

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Something went wrong: Database connection error"));
    }
}