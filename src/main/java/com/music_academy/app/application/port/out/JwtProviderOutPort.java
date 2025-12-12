package com.music_academy.app.application.port.out;

import com.music_academy.app.domain.model.User;

public interface JwtProviderOutPort {
	String generateToken(User user);
}
