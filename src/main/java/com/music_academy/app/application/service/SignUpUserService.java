package com.music_academy.app.application.service;

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
	private final CreateBucketDirectoryOutPort createBucketDirectoryOutPort;

	@Override
	public User signUp(String email, String password) {

		User user = new User(null, Role.USER, email, passwordEncoderOutPort.encode(password));

		User createdUser = userRepositoryOutPort.createUser(user);

		String pathPrefix = "user" + createdUser.id() + "/home/";

		Node node = new Node(null, null, createdUser, null, "home", NodeType.DIRECTORY, null, pathPrefix);

		createBucketDirectoryOutPort.createDirectory(pathPrefix, "");

		createUserDirectoryNodeOutPort.createNode(node);

		return createdUser;
	}
}
