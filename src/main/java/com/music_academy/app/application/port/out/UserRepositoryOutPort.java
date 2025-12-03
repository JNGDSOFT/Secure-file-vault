package com.music_academy.app.application.port.out;

import org.springframework.security.core.userdetails.UserDetails;

import com.music_academy.app.domain.model.User;

public interface UserRepositoryOutPort {
	User createUser(User user);

	User getUserById(Long id);
}
