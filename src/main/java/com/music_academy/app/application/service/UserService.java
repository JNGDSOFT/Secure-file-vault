package com.music_academy.app.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.CreateUserUseCase;
import com.music_academy.app.application.port.in.GetUserById;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.persistance.JpaRepositoryUserAdapter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements CreateUserUseCase, GetUserById {

	private final UserRepositoryOutPort userRepositoryOutPort;

	@Override
	public User createUser(User user) {
		return userRepositoryOutPort.createUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepositoryOutPort.getUserById(id);
	}
}
