package com.music_academy.app.application.port.out;

import java.util.UUID;

public interface TokenBlacklistRepositoryOutPort {

	void saveToken(UUID jti, Long instant);

	void cleanExpiratedTokens();
}
