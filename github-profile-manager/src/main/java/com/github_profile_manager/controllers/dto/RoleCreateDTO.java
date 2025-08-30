package com.github_profile_manager.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleCreateDTO(
        @NotBlank
        String name
) {
}
