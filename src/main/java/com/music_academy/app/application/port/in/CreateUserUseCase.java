package com.music_academy.app.application.port.in;

import com.music_academy.app.domain.model.User;

public interface CreateUserUseCase {
	User createUser(User user);
}
