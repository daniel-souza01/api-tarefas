package com.api.tarefas.modules.task.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String userId) {
		super("User not found with ID: " + userId);
	}
}
