package com.api.tarefas.modules.user.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String userId) {
		super("User not found with ID: " + userId);
	}
}
