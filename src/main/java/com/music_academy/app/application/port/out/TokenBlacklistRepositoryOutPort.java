package com.music_academy.app.application.port.out;

import java.util.UUID;

public interface TokenBlacklistRepositoryOutPort {

	void addTokenToBlacklist(UUID jti, Long instant);

	void cleanExpiratedTokens();

	boolean isTokenInBlacklist(UUID jti);
}
