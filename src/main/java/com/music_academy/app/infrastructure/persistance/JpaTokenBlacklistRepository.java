package com.music_academy.app.infrastructure.persistance;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.music_academy.app.application.port.out.TokenBlacklistRepositoryOutPort;
import com.music_academy.app.infrastructure.persistance.model.TokenBlacklistEntity;
import com.music_academy.app.infrastructure.security.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTokenBlacklistRepository implements TokenBlacklistRepositoryOutPort {

	private final SpringDataTokenBlacklistRepository springDataTokenBlacklistRepository;
	private final JwtUtil jwtUtil;

	private final Logger logger = LoggerFactory.getLogger(JpaTokenBlacklistRepository.class);

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
	@Transactional
	@Scheduled(cron = "0 0 0  * * ?")
	public void cleanExpiratedTokens() {
		springDataTokenBlacklistRepository.deleteByExpirationInstantLessThan(Instant.now().getEpochSecond());
		logger.info("Expired tokens were removed from blacklist");
	}

	@Override
	public boolean isTokenInBlacklist(UUID jti) {
		return springDataTokenBlacklistRepository.existsById(jti);
	}

}
