package com.api.tarefas.modules.task.exceptions;

public class TaskListNotBelongToUserException extends RuntimeException {
	public TaskListNotBelongToUserException() {
		super("This task list not belong to this user");
	}
}
