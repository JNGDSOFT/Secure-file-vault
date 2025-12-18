package com.music_academy.app.infrastructure.exception.handler;

import java.sql.SQLException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.music_academy.app.infrastructure.exception.handler.model.GenericExceptionResponseBody;

@RestControllerAdvice
public class PersistanceExceptionHandler {
	@ExceptionHandler(exception = SQLException.class)
	ResponseEntity<GenericExceptionResponseBody> test(SQLException sqlException) {
		String sqlState = sqlException.getSQLState();
		int code = Integer.parseInt(sqlState);
		String error = "";
		String message = "";
		if (code == 23505) {
			error = "duplicity";
			message = "That email already exists";
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(409))
				.body(GenericExceptionResponseBody.builder().error(error).message(message).build());
	}
}
