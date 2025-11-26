package com.music_academy.app.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileUseCase {
	String createFile(MultipartFile multipartFile);
}
