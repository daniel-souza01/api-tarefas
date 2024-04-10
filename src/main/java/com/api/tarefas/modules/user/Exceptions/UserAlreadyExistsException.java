package com.api.tarefas.modules.user.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException() {
		super("User already exists");
	}
}
