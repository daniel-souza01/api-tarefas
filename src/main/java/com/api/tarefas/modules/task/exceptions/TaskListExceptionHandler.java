package com.api.tarefas.modules.task.exceptions;

import com.api.tarefas.config.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskListExceptionHandler {
	@ExceptionHandler(TaskListNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleTaskListNotFound(TaskListNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
	}

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleTaskNotFound(TaskNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
	}

	@ExceptionHandler(TaskListNotBelongToUserException.class)
	public ResponseEntity<ErrorResponseDTO> handleTaskListNotBelongToUser(TaskListNotBelongToUserException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(exception.getMessage()));
	}
}
