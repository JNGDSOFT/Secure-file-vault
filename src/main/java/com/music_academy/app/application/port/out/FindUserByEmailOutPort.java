package com.music_academy.app.application.port.out;

import com.music_academy.app.domain.model.User;

public interface FindUserByEmailOutPort {
	User findUserByEmail(String email);
}
