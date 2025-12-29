package com.music_academy.app.infrastructure.controller.dto;

import java.util.UUID;

public record FileRequestDTO(Long userId, UUID targetNode) {

}
