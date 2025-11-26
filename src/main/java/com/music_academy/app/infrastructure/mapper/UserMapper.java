package com.music_academy.app.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.controller.dto.UserRequestDTO;
import com.music_academy.app.infrastructure.controller.dto.UserResponseDTO;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserEntity mapUserToEntity(User user);

	User mapEntityToUser(UserEntity userEntity);

	User mapRequestToUser(UserRequestDTO userRequestDTO);

	UserResponseDTO mapUserToResponseDTO(User user);
}
