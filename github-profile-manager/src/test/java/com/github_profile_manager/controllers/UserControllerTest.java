package com.github_profile_manager.controllers;

import com.github_profile_manager.controllers.dto.UserResponseDTO;
import com.github_profile_manager.entities.Role;
import com.github_profile_manager.exceptions.custom.ObjectNotFoundException;
import com.github_profile_manager.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void designateUserRole_ShouldReturn200() throws Exception {
        UserResponseDTO mockResponse = new UserResponseDTO(
                1L,
                "Teste",
                "https://teste/users/teste",
                Set.of(new Role(1L, "ADMIN"))
        );

        Mockito.when(userService.designateUserRole(1L, "ADMIN"))
                .thenReturn(mockResponse);

        mockMvc.perform(patch("/api/v1/users/1/roles")
                        .param("role", "ADMIN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("Teste"))
                .andExpect(jsonPath("$.roles[0].name").value("ADMIN"));
    }

    @Test
    void designateUserRole_ShouldReturn404_WhenUserNotFound() throws Exception {
        Mockito.when(userService.designateUserRole(99L, "ADMIN"))
                .thenThrow(new ObjectNotFoundException("User not found"));

        mockMvc.perform(patch("/api/v1/users/99/roles")
                        .param("role", "ADMIN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsers_ShouldReturn200WithUsers() throws Exception {
        List<UserResponseDTO> mockUsers = List.of(
                new UserResponseDTO(1L, "Pessoa 1", "https://github.com/pessoa1", Set.of(new Role(1L, "ADMIN"))),
                new UserResponseDTO(2L, "Pessoa 2", "https://github.com/pessoa2", Set.of(new Role(2L, "USER")))
        );

        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/v1/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].login").value("Pessoa 1"))
                .andExpect(jsonPath("$[1].login").value("Pessoa 2"));
    }

}
