package com.music_academy.app.infrastructure.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.music_academy.app.infrastructure.config.JwtProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

	private final JwtProperties jwtProperties;

	public String generateToken(String username) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtProperties.getExpiration());

		return Jwts.builder().subject(username).issuedAt(now).expiration(expiry)
				.signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()), SIG.HS256).compact();
	}

	public SecretKey getSigningKey() {
		byte[] keyBytes = jwtProperties.getSecret().getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}
}
