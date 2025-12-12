package com.music_academy.app.application.service;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.GetUserByIdUseCase;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUserByIdService implements GetUserByIdUseCase {

	private final UserRepositoryOutPort userRepositoryOutPort;

	@Override
	public User getUserById(Long id) {
		return userRepositoryOutPort.getUserById(id);
	}
}
