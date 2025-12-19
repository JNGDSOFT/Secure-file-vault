package com.music_academy.app.infrastructure.persistance;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.music_academy.app.application.port.out.TokenBlacklistRepositoryOutPort;
import com.music_academy.app.infrastructure.persistance.model.TokenBlacklistEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTokenBlacklistRepository implements TokenBlacklistRepositoryOutPort {

	private final SpringDataTokenBlacklistRepository springDataTokenBlacklistRepository;

	@Override
	public void saveToken(UUID jti, Long instant) {
		TokenBlacklistEntity tokenBlacklistEntity = new TokenBlacklistEntity();
		tokenBlacklistEntity.setJti(jti);
		tokenBlacklistEntity.setExpirationInstant(instant);

		springDataTokenBlacklistRepository.save(tokenBlacklistEntity);
	}

	@Override
	public void cleanExpiratedTokens() {
		// TODO Auto-generated method stub

	}

}
