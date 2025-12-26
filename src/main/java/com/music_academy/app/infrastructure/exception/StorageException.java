package com.music_academy.app.infrastructure.exception;

@SuppressWarnings("serial")
public class StorageException extends Exception {
	public StorageException(String reason) {
		super(reason);
	}

	public StorageException(String reason, Throwable cause) {
		super(reason, cause);
	}
}
