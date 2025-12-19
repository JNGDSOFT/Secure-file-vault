package com.music_academy.app.infrastructure.persistance.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "token_blacklist")
public class TokenBlacklistEntity {

	@Id
	private UUID jti;

	@Column(name = "expiration_instant", nullable = false)
	private Long expirationInstant;
}
