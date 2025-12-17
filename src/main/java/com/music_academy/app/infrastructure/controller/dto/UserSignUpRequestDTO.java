package com.music_academy.app.infrastructure.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignUpRequestDTO(@Size(max = 254) @Email @NotBlank String email,
		@Size(min = 8, max = 64) @NotBlank String password) {
}
