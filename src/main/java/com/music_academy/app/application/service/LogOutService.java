package com.music_academy.app.application.service;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.LogOutUseCase;
import com.music_academy.app.application.port.out.TokenBlacklistRepositoryOutPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogOutService implements LogOutUseCase {

	private final TokenBlacklistRepositoryOutPort tokenBlacklistRepositoryOutPort;

	@Override
	public void logOut(String token) {
		tokenBlacklistRepositoryOutPort.addTokenToBlacklist(token);
	}
}
