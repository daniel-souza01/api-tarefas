package com.api.tarefas.modules.task.exceptions;

public class TaskListAlreadyRemovedException extends RuntimeException {
	public TaskListAlreadyRemovedException() {
		super("Task list already removed");
	}
}
