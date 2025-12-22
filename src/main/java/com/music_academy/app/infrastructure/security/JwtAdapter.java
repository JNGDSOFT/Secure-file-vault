package com.music_academy.app.infrastructure.security;

import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.JwtProviderOutPort;
import com.music_academy.app.domain.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAdapter implements JwtProviderOutPort {
	private final JwtUtil jwtUtil;

	@Override
	public String generateToken(User user) {
		return jwtUtil.generateToken(user);
	}
}
