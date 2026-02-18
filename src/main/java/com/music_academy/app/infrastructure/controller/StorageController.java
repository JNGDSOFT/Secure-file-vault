package com.music_academy.app.infrastructure.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.music_academy.app.application.port.in.CreateFolderUseCase;
import com.music_academy.app.application.port.in.UploadFileUseCase;
import com.music_academy.app.infrastructure.controller.dto.CreateFolderRequestDTO;
import com.music_academy.app.infrastructure.exception.StorageException;
import com.music_academy.app.infrastructure.persistance.model.UserEntity;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class StorageController {
	private final UploadFileUseCase uploadFileUseCase;
	private final CreateFolderUseCase createFolderUseCase;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/upload")
	public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "parentId", required = false) UUID parentId, Authentication authentication)
			throws StorageException, IOException {

		Long userId = ((UserEntity) authentication.getPrincipal()).getId();

		uploadFileUseCase.uploadFile(userId, parentId, file);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/folder")
	public ResponseEntity<Void> createFolder(@RequestBody CreateFolderRequestDTO createFolderRequestDTO,
			Authentication authentication) throws StorageException {
		Long userId = ((UserEntity) authentication.getPrincipal()).getId();
		createFolderUseCase.createFolder(userId, createFolderRequestDTO.parentNode(), createFolderRequestDTO.name());

		return ResponseEntity.noContent().build();
	}
}
