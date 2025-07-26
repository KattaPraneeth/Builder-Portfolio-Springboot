package com.praneeth.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.praneeth.Controller.ProjectController;
import com.praneeth.DTO.ProjectDTO;
import com.praneeth.Entity.Project;
import com.praneeth.Enum.ProjectStatus;
import com.praneeth.Service.Interfaces.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void createProject_shouldReturn201() throws Exception {
        ProjectDTO dto = new ProjectDTO();
        dto.setTitle("New Tower");
        dto.setDescription("20 floors");
        dto.setStatus(ProjectStatus.UPCOMING);
        dto.setClientId(1L);
        dto.setBuilderId(2L);

        Project created = new Project();
        created.setId(100L);
        created.setTitle(dto.getTitle());
        created.setStatus(dto.getStatus());

        when(projectService.addProject(any(ProjectDTO.class))).thenReturn(created);

        String payload = new ObjectMapper().writeValueAsString(dto);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100));
    }
}
