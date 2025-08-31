package com.github_profile_manager.controllers;

import com.github_profile_manager.controllers.dto.LoginRequestDTO;
import com.github_profile_manager.controllers.dto.LoginResponseDTO;
import com.github_profile_manager.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        return ResponseEntity.ok().body(authService.login(dto));
    }
}
