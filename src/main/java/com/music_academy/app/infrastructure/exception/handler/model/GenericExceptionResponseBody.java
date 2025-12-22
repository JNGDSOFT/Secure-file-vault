package com.music_academy.app.infrastructure.exception.handler.model;

import java.time.LocalDateTime;

import com.music_academy.app.infrastructure.utils.URIUtils;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenericExceptionResponseBody {
	private final String timestamp = LocalDateTime.now().toString();
	private int status;
	private String error;
	private String message;
	private final String path = URIUtils.getCurrentPath();
}
