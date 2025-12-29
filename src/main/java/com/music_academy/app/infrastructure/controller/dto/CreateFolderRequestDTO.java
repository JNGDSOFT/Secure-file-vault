package com.music_academy.app.infrastructure.controller.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateFolderRequestDTO(UUID parentNode,
		@NotBlank(message = "you have to set a name for the new folder") String name) {

}
