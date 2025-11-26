package com.music_academy.app.application.service;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.EncodePasswordUseCase;

@Service
public class PasswordEncodeService implements EncodePasswordUseCase {

	@Override
	public String encodePassword(String password) {
		return null;
	}
}
