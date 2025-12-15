package com.music_academy.app.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.music_academy.app.application.port.in.GetUserByIdUseCase;
import com.music_academy.app.application.port.in.SignUpUserUseCase;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.controller.dto.UserRequestDTO;
import com.music_academy.app.infrastructure.controller.dto.UserResponseDTO;
import com.music_academy.app.infrastructure.mapper.UserMapper;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;
import com.music_academy.app.infrastructure.utils.URIUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserAuthenticationController {

	private final SignUpUserUseCase signUpUserUseCase;
	private final GetUserByIdUseCase getUserById;
	private final UserMapper userMapper;

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> signUpUser(@RequestBody UserRequestDTO userRequestDTO) {

		final User user = userMapper.mapRequestToUser(userRequestDTO);

		final User createdUser = signUpUserUseCase.signUp(user.email(), user.password());

		final UserResponseDTO userResponseDTO = userMapper.mapUserToResponseDTO(createdUser);

		return ResponseEntity.created(null).body(userResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {
		final User foundUser = getUserById.getUserById(id);
		final UserResponseDTO userResponseDTO = userMapper.mapUserToResponseDTO(foundUser);

		return ResponseEntity.ok(userResponseDTO);
	}
}
