package com.music_academy.app.application.port.in;

import com.music_academy.app.domain.model.User;

public interface GetUserById {
	User getUserById(Long id);
}
