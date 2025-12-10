package com.music_academy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.music_academy.app.application.port.out.PasswordEncoderOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.application.service.UserService;
import com.music_academy.app.domain.model.Role;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	@Mock
	private UserRepositoryOutPort userRepositoryOutPort;

	@Mock
	private PasswordEncoderOutPort passwordEncoderOutPort;

	@InjectMocks
	private UserService userService;

	@Test
	public void createUser_returnsUserWithTransferedData_whenUserIsValid() {

		String password = "abejitamaya123";
		String expectedEncodedPassword = "ENCRYPTED";
		User user = new User(null, Role.USER, "ericardio@prueba.com", password);
		when(passwordEncoderOutPort.encode(password)).thenReturn(expectedEncodedPassword);
		when(userRepositoryOutPort.createUser(any(User.class)))
				.thenReturn(new User(1l, Role.USER, "ericardio@prueba.com", expectedEncodedPassword));

		User result = userService.createUser(user);

		assertEquals(user.email(), result.email());
		assertEquals(expectedEncodedPassword, result.password());
		assertEquals(1l, result.id());
	}

	@Test
	public void getUserById_returnsUser_whenUserExist() {
		User existingUser = new User(1l, Role.USER, "ericardio@prueba.com", "abejitamaya123");
		Long searchId = 1l;
		when(userRepositoryOutPort.getUserById(1l)).thenReturn(existingUser);

		User result = userService.getUserById(searchId);

		assertEquals(existingUser.id(), result.id());
		assertEquals(existingUser.email(), result.email());
		assertEquals(existingUser.password(), result.password());
	}
}
