package com.music_academy.app.infrastructure.exception.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.music_academy.app.infrastructure.exception.handler.model.GenericExceptionResponseBody;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
	@ExceptionHandler(exception = BadCredentialsException.class)
	public ResponseEntity<GenericExceptionResponseBody> handleBadCretendialsException(
			BadCredentialsException badCredentialsException) {
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(
				GenericExceptionResponseBody.builder().status(403).error("forbidden").message("access_denied").build());
	}
}
