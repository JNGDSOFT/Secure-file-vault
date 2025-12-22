package com.music_academy.app.infrastructure.utils;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public class ServletUtils {
	public static Optional<String> extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return Optional.empty();
		}

		return Optional.of(authHeader.substring(7));
	}
}
