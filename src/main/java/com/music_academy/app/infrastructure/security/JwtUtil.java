package com.music_academy.app.infrastructure.security;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.config.JwtPropertiesConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	private final JwtPropertiesConfig jwtProperties;

	public String generateToken(User user) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtProperties.getExpirationMillis());

		return Jwts.builder().subject(user.email()).claim("userId", user.id()).claim("roles", user.roles())
				.claim("jti", UUID.randomUUID().toString()).issuedAt(now).expiration(expiry)
				.signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()), SIG.HS256).compact();
	}

	public SecretKey getSigningKey() {
		byte[] keyBytes = jwtProperties.getSecret().getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public Claims getClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}
}
