package com.github_profile_manager.controllers;


import com.github_profile_manager.controllers.dto.UserResponseDTO;
import com.github_profile_manager.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> designateUserRole(@PathVariable Long id, @RequestParam String role){
        return ResponseEntity.ok().body(userService.designateUserRole(id, role));
    }
}
