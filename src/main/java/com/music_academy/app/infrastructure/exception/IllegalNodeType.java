package com.music_academy.app.infrastructure.exception;

public class IllegalNodeType extends StorageException {

	public IllegalNodeType(String reason) {
		super(reason);
	}
}
