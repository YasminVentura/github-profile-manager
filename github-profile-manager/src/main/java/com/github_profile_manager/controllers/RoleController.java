package com.github_profile_manager.controllers;

import com.github_profile_manager.controllers.dto.RoleCreateDTO;
import com.github_profile_manager.controllers.dto.RoleResponseDTO;
import com.github_profile_manager.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> save(@RequestBody @Valid RoleCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.registerRole(dto));
    }
}
