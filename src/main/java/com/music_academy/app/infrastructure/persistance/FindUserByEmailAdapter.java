package com.music_academy.app.infrastructure.persistance;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.music_academy.app.application.port.out.FindUserByEmailOutPort;
import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.NotFoundByNameException;
import com.music_academy.app.infrastructure.mapper.UserMapper;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FindUserByEmailAdapter implements FindUserByEmailOutPort {
	private final SpringDataUserRepository springDataUserRepository;
	private final UserMapper userMapper;

	@Override
	public Optional<User> findUserByEmail(String email) {
		// TODO improve those methods here
		UserEntity userEntity = springDataUserRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundByNameException());
		Optional<User> user = Optional.of(userMapper.mapEntityToUser(userEntity));
		return user;
	}
}
