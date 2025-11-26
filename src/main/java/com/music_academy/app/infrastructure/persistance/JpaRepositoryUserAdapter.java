package com.music_academy.app.infrastructure.persistance;

import org.springframework.stereotype.Repository;

import com.music_academy.app.application.port.out.UserRepositoryOutPort;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.mapper.UserMapper;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JpaRepositoryUserAdapter implements UserRepositoryOutPort {
	private final SpringDataUserRepository springDataUserRepository;
	private final UserMapper userMapper;

	@Override
	public User createUser(User user) {
		final UserEntity userEntity = userMapper.mapUserToEntity(user);
		final UserEntity createdEntity = springDataUserRepository.save(userEntity);
		return userMapper.mapEntityToUser(createdEntity);
	}
}
