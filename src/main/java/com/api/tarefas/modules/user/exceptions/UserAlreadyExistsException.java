package com.api.tarefas.modules.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException() {
		super("User already exists");
	}
}
