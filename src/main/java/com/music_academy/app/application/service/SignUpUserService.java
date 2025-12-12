package com.music_academy.app.application.service;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.SignUpUserUseCase;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Role;
import com.music_academy.app.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpUserService implements SignUpUserUseCase {

	private final UserRepositoryOutPort userRepositoryOutPort;

	@Override
	public void signUp(String email, String password) {
		User user = new User(null, Role.USER, email, password);
		userRepositoryOutPort.createUser(user);
	}
}
