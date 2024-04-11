package com.api.tarefas.modules.task.exceptions;

public class TaskNotFoundException extends RuntimeException {
	public TaskNotFoundException(String taskId) {
		super("Task not found with ID: " + taskId);
	}
}
