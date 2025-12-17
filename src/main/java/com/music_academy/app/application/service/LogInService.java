package com.music_academy.app.application.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.LogInUserUseCase;
import com.music_academy.app.application.port.out.FindUserByEmailOutPort;
import com.music_academy.app.application.port.out.JwtProviderOutPort;
import com.music_academy.app.application.port.out.PasswordEncoderOutPort;
import com.music_academy.app.domain.model.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogInService implements LogInUserUseCase {

	private final FindUserByEmailOutPort findUserByEmailOutPort;
	private final PasswordEncoderOutPort passwordEncoderOutPort;
	private final JwtProviderOutPort jwtProviderOutPort;

	@Override
	public String logIn(String email, String password) {
		// TODO check if there is a better exception throw
		User user = findUserByEmailOutPort.findUserByEmail(email);
		boolean matches = passwordEncoderOutPort.matches(password, user.password());
		if (!matches) {
			throw new BadCredentialsException("The user is invalid");
		}
		return jwtProviderOutPort.generateToken(user);
	}
}
