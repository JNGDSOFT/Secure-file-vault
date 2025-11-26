package com.music_academy.app.application.service;

import com.music_academy.app.application.port.in.CreateUserUseCase;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.persistance.JpaRepositoryUserAdapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements CreateUserUseCase {

	private final JpaRepositoryUserAdapter jpaRepositoryUserAdapter;

	@Override
	public User createUser(User user) {
		return jpaRepositoryUserAdapter.createUser(user);
	}
}
