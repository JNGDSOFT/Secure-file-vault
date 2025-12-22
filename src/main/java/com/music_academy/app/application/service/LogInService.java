package com.music_academy.app.application.service;

import java.util.Optional;

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

	private static final String DUMMY_PASSWORD = "anypassw231111";
	private static final String DUMMY_HASH = "$2a$10$0stRnk.Owpw3BhveuAwlTOYDDD/RJTD93Ugl5vtUb8dY/aXNmbZNe";
	private final FindUserByEmailOutPort findUserByEmailOutPort;
	private final PasswordEncoderOutPort passwordEncoderOutPort;
	private final JwtProviderOutPort jwtProviderOutPort;

	@Override
	public String logIn(String email, String password) {

		Optional<User> userOptional = findUserByEmailOutPort.findUserByEmail(email);

		if (userOptional.isEmpty()) {
			passwordEncoderOutPort.matches(DUMMY_PASSWORD, DUMMY_HASH);
			throw new BadCredentialsException("User email was not found");
		}

		User user = userOptional.get();

		boolean matches = passwordEncoderOutPort.matches(password, user.password());
		if (!matches) {
			throw new BadCredentialsException("The user password is invalid");
		}
		return jwtProviderOutPort.generateToken(user);
	}
}

