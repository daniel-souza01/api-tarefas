package com.api.tarefas.modules.user.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity handleUserAlreadyExists(UserAlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
}
