package com.music_academy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.music_academy.app.application.port.out.FindUserByEmailOutPort;
import com.music_academy.app.application.port.out.JwtProviderOutPort;
import com.music_academy.app.application.port.out.PasswordEncoderOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.application.service.GetUserByIdService;
import com.music_academy.app.application.service.LogInService;
import com.music_academy.app.application.service.SignUpUserService;
import com.music_academy.app.domain.model.Role;
import com.music_academy.app.domain.model.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {
	@Mock
	private UserRepositoryOutPort userRepositoryOutPort;

	@Mock
	private PasswordEncoderOutPort passwordEncoderOutPort;

	@Mock
	private UserDetailsService userDetailsService;

	@Mock
	private FindUserByEmailOutPort findUserByEmailOutPort;

	@Mock
	private JwtProviderOutPort jwtProviderOutPort;

	@InjectMocks
	private SignUpUserService signUpUserService;

	@InjectMocks
	private LogInService userService;

	@InjectMocks
	private GetUserByIdService getUserByIdService;

	@InjectMocks
	private LogInService logInService;

	@Test
	void createUser_returnsVoidWithTransferedData_whenUserIsValid() {

		String email = "ericardio@prueba.com";
		String password = "abejitamaya123";
		String expectedEncodedPassword = "ENCRYPTED";

		// TODO check this mocks
		when(passwordEncoderOutPort.encode(password)).thenReturn(expectedEncodedPassword);
		when(userRepositoryOutPort.createUser(any(User.class)))
				.thenReturn(new User(1l, List.of(Role.USER), "ericardio@prueba.com", expectedEncodedPassword));

		signUpUserService.signUp(email, password);
	}

	@Test
	void getUserById_returnsUser_whenUserExist() {
		User existingUser = new User(1l, List.of(Role.USER), "ericardio@prueba.com", "abejitamaya123");
		Long searchId = 1l;
		when(userRepositoryOutPort.getUserById(1l)).thenReturn(existingUser);

		User result = getUserByIdService.getUserById(searchId);

		assertEquals(existingUser.id(), result.id());
		assertEquals(existingUser.email(), result.email());
		assertEquals(existingUser.password(), result.password());
	}

	@Test
	void logIn_returnsToken_whenUserIsValid() {
		String email = "ericardio@prueba.com";
		String password = "abejitamaya123";
		User user = new User(1l, List.of(Role.USER), email, "Encriptadita");

		when(findUserByEmailOutPort.findUserByEmail(email)).thenReturn(user);
		when(passwordEncoderOutPort.matches(password, user.password())).thenReturn(true);
		when(jwtProviderOutPort.generateToken(user)).thenReturn("tokenFeliz");

		String actualToken = logInService.logIn(email, password);

		assertEquals("tokenFeliz", actualToken);
	}
}
