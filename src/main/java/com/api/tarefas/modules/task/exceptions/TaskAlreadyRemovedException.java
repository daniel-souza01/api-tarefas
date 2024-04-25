package com.api.tarefas.modules.task.exceptions;

public class TaskAlreadyRemovedException extends RuntimeException {
	public TaskAlreadyRemovedException() {
		super("Task already removed");
	}
}
