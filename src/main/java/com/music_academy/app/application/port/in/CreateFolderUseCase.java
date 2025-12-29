package com.music_academy.app.application.port.in;

import java.util.UUID;

import com.music_academy.app.infrastructure.exception.StorageException;

public interface CreateFolderUseCase {
	void createFolder(Long ownerUserId, UUID parentNodeUuid, String name) throws StorageException;
}
