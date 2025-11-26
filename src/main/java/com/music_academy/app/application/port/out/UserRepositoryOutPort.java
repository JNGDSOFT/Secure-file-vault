package com.music_academy.app.application.port.out;

import com.music_academy.app.domain.model.User;

public interface UserRepositoryOutPort {
	User createUser(User user);
}
