package com.music_academy.app.application.port.in;

import com.music_academy.app.domain.model.User;

public interface GetUserByIdUseCase {
	User getUserById(Long id);
}
