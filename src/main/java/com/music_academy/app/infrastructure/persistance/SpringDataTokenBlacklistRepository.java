package com.music_academy.app.infrastructure.persistance;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music_academy.app.infrastructure.persistance.model.TokenBlacklistEntity;

public interface SpringDataTokenBlacklistRepository extends JpaRepository<TokenBlacklistEntity, UUID> {
	void deleteByExpirationInstantLessThan(Long expirationInstant);
}
