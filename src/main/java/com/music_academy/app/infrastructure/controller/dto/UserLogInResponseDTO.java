package com.music_academy.app.infrastructure.controller.dto;

import lombok.Builder;

@Builder
public record UserLogInResponseDTO(String token) {

}
