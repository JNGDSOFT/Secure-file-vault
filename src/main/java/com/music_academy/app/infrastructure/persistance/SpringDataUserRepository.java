package com.music_academy.app.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

}
