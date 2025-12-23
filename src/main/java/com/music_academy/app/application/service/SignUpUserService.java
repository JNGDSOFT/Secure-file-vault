package com.music_academy.app.application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.music_academy.app.application.port.in.SignUpUserUseCase;
import com.music_academy.app.application.port.out.CreateBucketDirectoryOutPort;
import com.music_academy.app.application.port.out.CreateUserDirectoryNodeOutPort;
import com.music_academy.app.application.port.out.PasswordEncoderOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.NodeType;
import com.music_academy.app.domain.model.Role;
import com.music_academy.app.domain.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpUserService implements SignUpUserUseCase {

	private final UserRepositoryOutPort userRepositoryOutPort;
	private final PasswordEncoderOutPort passwordEncoderOutPort;
	private final CreateUserDirectoryNodeOutPort createUserDirectoryNodeOutPort;

	@Override
	public User signUp(String email, String password) {

		User user = new User(null, Set.of(Role.USER), email, passwordEncoderOutPort.encode(password));

		User createdUser = userRepositoryOutPort.createUser(user);

		createUserDirectoryNodeOutPort.createNode(new Node(UUID.randomUUID(), null, createdUser, Instant.now(), null,
				NodeType.DIRECTORY, null, null, null, ""));

		return createdUser;
	}
}
