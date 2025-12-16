package com.music_academy.app.application.service;

import com.music_academy.app.application.port.in.CreateDirectoryUseCase;
import com.music_academy.app.application.port.in.CreateFileUseCase;
import com.music_academy.app.application.port.out.CreateUserDirectoryNodeOutPort;
import com.music_academy.app.domain.model.Node;

public class CreateNodeService implements CreateDirectoryUseCase, CreateFileUseCase {
	CreateUserDirectoryNodeOutPort createUserDirectoryNodeOutPort;

	@Override
	public void createFile(Node node) {
		
	}

	@Override
	public void createDirectory(Node node) {
		// TODO Auto-generated method stub
	}

}
