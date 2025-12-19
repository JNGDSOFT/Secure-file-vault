package com.music_academy.app.application.port.out;

import java.util.UUID;

public interface TokenBlacklistRepositoryOutPort {

	void addTokenToBlacklist(String token);

	void cleanExpiratedTokens();

	boolean isTokenInBlacklist(UUID jti);
}
