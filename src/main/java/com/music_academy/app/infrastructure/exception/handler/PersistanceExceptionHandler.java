package com.music_academy.app.infrastructure.exception.handler;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sun.jdi.Type;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VoidValue;

@RestControllerAdvice
public class PersistanceExceptionHandler {
	@ExceptionHandler(exception = SQLException.class)
	ResponseEntity<String> test(SQLException sqlException) {
		String sqlState = sqlException.getSQLState();
		int code = Integer.parseInt(sqlState);
		String message = "";
		if (code == 23505) {
			message = "That email already exists";
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(message);
	}
}
