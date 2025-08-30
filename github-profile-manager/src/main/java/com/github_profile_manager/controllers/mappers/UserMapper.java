package com.github_profile_manager.controllers.mappers;

import com.github_profile_manager.controllers.dto.UserResponseDTO;
import com.github_profile_manager.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);
}
