package com.github_profile_manager.services;

import com.github_profile_manager.controllers.dto.UserResponseDTO;
import com.github_profile_manager.controllers.mappers.UserMapper;
import com.github_profile_manager.entities.Role;
import com.github_profile_manager.entities.User;
import com.github_profile_manager.exceptions.custom.ObjectNotFoundException;
import com.github_profile_manager.repositories.RoleRepository;
import com.github_profile_manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO designateUserRole(Long id, String rolePath) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with ID: " + id));

        Role role = roleRepository.findByName(rolePath)
                .orElseThrow(() -> new ObjectNotFoundException("Role not found with role: " + rolePath));

        user.getRoles().add(role);

        return userMapper.toResponseDTO(userRepository.save(user));
    }
}
