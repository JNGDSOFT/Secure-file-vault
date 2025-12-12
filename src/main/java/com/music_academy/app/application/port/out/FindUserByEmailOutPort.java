package com.music_academy.app.application.port.out;

import java.util.Optional;

import com.music_academy.app.domain.model.User;

public interface FindUserByEmailOutPort {
	Optional<User> findUserByEmail(String email);
}
