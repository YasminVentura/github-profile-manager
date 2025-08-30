package com.github_profile_manager.services;

import com.github_profile_manager.controllers.dto.RoleCreateDTO;
import com.github_profile_manager.controllers.dto.RoleResponseDTO;
import com.github_profile_manager.controllers.mappers.RoleMapper;
import com.github_profile_manager.repositories.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleResponseDTO registerRole(@Valid RoleCreateDTO dto) {
        var role = roleMapper.toEntity(dto);
        return roleMapper.toDto(roleRepository.save(role));
    }
}
