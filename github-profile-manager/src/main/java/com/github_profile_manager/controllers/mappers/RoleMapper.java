package com.github_profile_manager.controllers.mappers;

import com.github_profile_manager.controllers.dto.RoleCreateDTO;
import com.github_profile_manager.controllers.dto.RoleResponseDTO;
import com.github_profile_manager.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleCreateDTO dto);

    RoleResponseDTO toDto(Role entity);
}
