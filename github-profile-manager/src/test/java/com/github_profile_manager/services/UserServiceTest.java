package com.github_profile_manager.services;

import com.github_profile_manager.controllers.dto.UserResponseDTO;
import com.github_profile_manager.controllers.mappers.UserMapper;
import com.github_profile_manager.entities.Role;
import com.github_profile_manager.entities.User;
import com.github_profile_manager.exceptions.custom.ObjectNotFoundException;
import com.github_profile_manager.repositories.RoleRepository;
import com.github_profile_manager.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setLogin("Teste");
        user.setUrl("https://teste.com/Ttste");

        role = new Role(1L, "ADMIN");
    }

    @Test
    void designateUserRole_ShouldAddRoleAndReturnDTO() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(role));
        when(userRepository.save(user)).thenReturn(user);

        UserResponseDTO expectedDTO = new UserResponseDTO(
                1L, "Teste", "https://teste.com/Ttste", Set.of(role)
        );
        when(userMapper.toResponseDTO(user)).thenReturn(expectedDTO);

        UserResponseDTO result = userService.designateUserRole(1L, "ADMIN");

        assertNotNull(result);
        assertEquals("Teste", result.login());
        assertTrue(result.roles().stream().anyMatch(r -> r.getName().equals("ADMIN")));

        verify(userRepository).findById(1L);
        verify(roleRepository).findByName("ADMIN");
        verify(userRepository).save(user);
        verify(userMapper).toResponseDTO(user);
    }

    @Test
    void designateUserRole_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        ObjectNotFoundException ex = assertThrows(
                ObjectNotFoundException.class,
                () -> userService.designateUserRole(99L, "ADMIN")
        );

        assertEquals("User not found with ID: 99", ex.getMessage());
        verify(userRepository).findById(99L);
        verifyNoInteractions(roleRepository, userMapper);
    }
}
