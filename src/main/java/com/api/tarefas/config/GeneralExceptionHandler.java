package com.api.tarefas.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handlerMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		List<ValidationErrorDTO> fieldErrors = exception.getFieldErrors().stream().map(err -> {
			return new ValidationErrorDTO(err.getField(), err.getDefaultMessage());
		}).toList();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrors);
	}
}
