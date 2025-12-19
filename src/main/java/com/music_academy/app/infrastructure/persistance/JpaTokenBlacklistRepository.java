package com.music_academy.app.infrastructure.persistance;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.music_academy.app.application.port.out.TokenBlacklistRepositoryOutPort;
import com.music_academy.app.infrastructure.persistance.model.TokenBlacklistEntity;
import com.music_academy.app.infrastructure.security.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTokenBlacklistRepository implements TokenBlacklistRepositoryOutPort {

	private final SpringDataTokenBlacklistRepository springDataTokenBlacklistRepository;
	private final JwtUtil jwtUtil;

	@Override
	public void addTokenToBlacklist(String token) {

		Claims claims = jwtUtil.getClaims(token);

		String jti = claims.get("jti", String.class);

		Long instant = claims.get("exp", Long.class);

		TokenBlacklistEntity tokenBlacklistEntity = new TokenBlacklistEntity();
		tokenBlacklistEntity.setJti(UUID.fromString(jti));
		tokenBlacklistEntity.setExpirationInstant(instant);

		springDataTokenBlacklistRepository.save(tokenBlacklistEntity);
	}

	@Override
	public void cleanExpiratedTokens() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTokenInBlacklist(UUID jti) {
		return springDataTokenBlacklistRepository.existsById(jti);
	}

}
