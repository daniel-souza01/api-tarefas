package com.api.tarefas.modules.task.exceptions;

public class TaskListNotFoundException extends RuntimeException {
	public TaskListNotFoundException(String taskListId) {
		super("Task list not found with ID: " + taskListId);
	}
}
