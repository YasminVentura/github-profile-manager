package com.github_profile_manager.controllers.dto;

import com.github_profile_manager.entities.Role;

import java.util.Set;

public record UserResponseDTO (
        Long id,
        String login,
        String url,
        Set<Role> roles
) {
}
