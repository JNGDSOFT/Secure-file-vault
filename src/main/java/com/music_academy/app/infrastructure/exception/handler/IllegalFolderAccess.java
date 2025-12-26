package com.music_academy.app.infrastructure.exception.handler;

import com.music_academy.app.infrastructure.exception.StorageException;

public class IllegalFolderAccess extends StorageException {

	public IllegalFolderAccess(String reason) {
		super(reason);
	}

}
