package com.music_academy.app.infrastructure.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.music_academy.app.application.port.in.GetUserByIdUseCase;
import com.music_academy.app.application.port.in.LogInUserUseCase;
import com.music_academy.app.application.port.in.LogOutUseCase;
import com.music_academy.app.application.port.in.SignUpUserUseCase;
import com.music_academy.app.application.service.LogOutService;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.controller.dto.UserSignUpRequestDTO;
import com.music_academy.app.infrastructure.controller.dto.UserLogInRequestDTO;
import com.music_academy.app.infrastructure.controller.dto.UserLogInResponseDTO;
import com.music_academy.app.infrastructure.controller.dto.UserResponseDTO;
import com.music_academy.app.infrastructure.mapper.UserMapper;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;
import com.music_academy.app.infrastructure.utils.ServletUtils;
import com.music_academy.app.infrastructure.utils.URIUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserAuthenticationController {
	private final SignUpUserUseCase signUpUserUseCase;
	private final LogInUserUseCase logInUserUseCase;
	private final GetUserByIdUseCase getUserById;
	private final LogOutUseCase logOutUseCase;
	private final UserMapper userMapper;

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> signUpUser(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {

		final User user = userMapper.mapSignUpRequestToUser(userSignUpRequestDTO);

		final User createdUser = signUpUserUseCase.signUp(user.email(), user.password());

		final UserResponseDTO userResponseDTO = userMapper.mapUserToResponseDTO(createdUser);

		URI uri = URIUtils.getCreatedElementURI("/login", createdUser.id());

		return ResponseEntity.created(uri).body(userResponseDTO);
	}

	@GetMapping("/login")
	public ResponseEntity<UserLogInResponseDTO> logInUser(@RequestBody UserLogInRequestDTO userLogInRequestDTO) {

		String token = logInUserUseCase.logIn(userLogInRequestDTO.email(), userLogInRequestDTO.password());
		UserLogInResponseDTO userLogInResponseDTO = UserLogInResponseDTO.builder().token(token).build();

		return ResponseEntity.ok(userLogInResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {
		final User foundUser = getUserById.getUserById(id);
		final UserResponseDTO userResponseDTO = userMapper.mapUserToResponseDTO(foundUser);

		return ResponseEntity.ok(userResponseDTO);
	}

	@GetMapping("/logout")
	public ResponseEntity<Void> logOutUser(HttpServletRequest httpServletRequest) {
		logOutUseCase.logOut(ServletUtils.extractToken(httpServletRequest).orElseThrow(
				() -> new BadCredentialsException("There is no authorization header in the servlet request")));
		return ResponseEntity.noContent().build();
	}
}
