package com.music_academy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.music_academy.app.application.port.out.PasswordEncoderOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.application.service.UserService;
import com.music_academy.app.domain.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	@Mock
	private UserRepositoryOutPort userRepositoryOutPort;

	@Mock
	private PasswordEncoderOutPort passwordEncoderOutPort;

	@InjectMocks
	private UserService userService;

	@Test
	public void CreateUser_returnsUserWithTransferedData_whenUserIsValid() {

		String password = "abejitamaya123";
		String expectedEncodedPassword = "ENCRYPTED";
		User user = new User(null, "ericardio@prueba.com", password);
		when(passwordEncoderOutPort.encode(password)).thenReturn(expectedEncodedPassword);
		when(userRepositoryOutPort.createUser(any(User.class)))
				.thenReturn(new User(1l, "ericardio@prueba.com", expectedEncodedPassword));
		User result = userService.createUser(user);

		assertEquals(user.email(), result.email());
		assertEquals(expectedEncodedPassword, result.password());
		assertEquals(1l, result.id());
	}
}
