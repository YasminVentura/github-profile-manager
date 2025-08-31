package com.github_profile_manager.services;

import com.github_profile_manager.controllers.dto.LoginRequestDTO;
import com.github_profile_manager.controllers.dto.LoginResponseDTO;
import com.github_profile_manager.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponseDTO(token);
    }
}
