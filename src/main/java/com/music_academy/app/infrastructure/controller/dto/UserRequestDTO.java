package com.music_academy.app.infrastructure.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@Email @NotBlank String email, @NotBlank String password) {

}
