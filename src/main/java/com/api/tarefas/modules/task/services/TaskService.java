package com.api.tarefas.modules.task.services;

import com.api.tarefas.modules.task.entities.Task;
import com.api.tarefas.modules.task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
	private final TaskRepository taskRepository;

	public List<Task> getAllByTaskListId(String taskListId) {
		return this.taskRepository.findByTaskListId(taskListId);
	}
}
