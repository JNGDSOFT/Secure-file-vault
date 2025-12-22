package com.music_academy.app.infrastructure.exception.util;

import com.music_academy.app.infrastructure.exception.handler.model.GenericExceptionResponseBody;

public class ExceptionResponseBodyFactory {
	public static GenericExceptionResponseBody unauthorizedGenericResponseBody() {
		return GenericExceptionResponseBody.builder().error("forbidden").message("access_denied").build();
	};

}
