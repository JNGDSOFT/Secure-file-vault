package com.music_academy.app.application.port.in;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.music_academy.app.infrastructure.exception.StorageException;

public interface UploadFileUseCase {
	void uploadFile(Long userId, UUID parentNodeId, MultipartFile multipartFile) throws StorageException, IOException;
}
