package com.music_academy.app.infrastructure.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;
import java.util.List;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);
}
