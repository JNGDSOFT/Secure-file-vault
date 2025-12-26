package com.music_academy.app.application.port.out;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.music_academy.app.domain.model.Node;
import com.music_academy.app.domain.model.User;
import com.music_academy.app.infrastructure.exception.StorageException;

public interface FilesBucketOutPort {

	void createFile(byte[] binary, Node node) throws IOException, StorageException;
}
