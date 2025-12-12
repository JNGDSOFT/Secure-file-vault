package com.music_academy.app.infrastructure.encode;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.music_academy.app.application.port.out.PasswordEncoderOutPort;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PasswordEncodeAdapter implements PasswordEncoderOutPort {

	private final PasswordEncoder passwordEncoder;

	@Override
	public String encode(String password) {

		return passwordEncoder.encode(password);
	}

	@Override
	public boolean matches(String rawPassword, String hashedPassword) {
		return passwordEncoder.matches(rawPassword, hashedPassword);
	}
}
