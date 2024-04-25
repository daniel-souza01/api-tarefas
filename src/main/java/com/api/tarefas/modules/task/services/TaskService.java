package com.api.tarefas.modules.task.services;

import com.api.tarefas.modules.task.dto.CreateTaskRequestDTO;
import com.api.tarefas.modules.task.dto.CreateTaskResponseDTO;
import com.api.tarefas.modules.task.dto.TaskResponseDTO;
import com.api.tarefas.modules.task.dto.UpdateTaskRequestDTO;
import com.api.tarefas.modules.task.entities.Task;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.TaskAlreadyRemovedException;
import com.api.tarefas.modules.task.exceptions.TaskListNotBelongToUserException;
import com.api.tarefas.modules.task.exceptions.TaskNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskRepository;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskListService taskListService;

	public Task getTaskById(String taskId) {
		return this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
	}

	public List<Task> getAllByTaskListId(String taskListId) {
		return this.taskRepository.findByTaskListId(taskListId);
	}

	public TaskResponseDTO getTaskDetails(String taskId) {
		Task task = this.getTaskById(taskId);
		return new TaskResponseDTO(
				task.getId(),
				task.getUser().getId(),
				task.getTaskList().getId(),
				task.getTitle(),
				task.getDate(),
				task.getIsFavorite(),
				task.getCompletedAt(),
				task.getRemovedAt(),
				task.getUpdatedAt(),
				task.getCreatedAt()
		);
	}

	public CreateTaskResponseDTO createTask(CreateTaskRequestDTO task) {
		User user = this.userService.getUserById(task.userId());
		TaskList taskList = this.taskListService.getTaskListById(task.taskListId());

		if (!taskList.getUser().getId().equals(user.getId())) {
			throw new TaskListNotBelongToUserException();
		}

		Task newTask = new Task();
		newTask.setUser(user);
		newTask.setTaskList(taskList);
		newTask.setTitle(task.title());
		newTask.setDate(task.date());
		newTask.setIsFavorite(Boolean.valueOf(task.isFavorite()));

		this.taskRepository.save(newTask);

		return new CreateTaskResponseDTO(newTask.getId());
	}

	public void updateTask(UpdateTaskRequestDTO data) {
		Task task = this.getTaskById(data.taskId());

		if (data.title() != null && !data.title().isBlank()) task.setTitle(data.title());

		if (data.isFavorite() != null && !data.isFavorite().isEmpty())
			task.setIsFavorite(Boolean.valueOf(data.isFavorite()));

		if (data.date() != null) task.setDate(data.date());
		if (data.completedAt() != null) task.setCompletedAt(data.completedAt());

		this.taskRepository.save(task);
		return;
	}

	public void removeTask(String taskId) {
		Task task = this.getTaskById(taskId);

		if (task.getRemovedAt() != null) {
			throw new TaskAlreadyRemovedException();
		}

		task.setRemovedAt(LocalDateTime.now());
		this.taskRepository.save(task);
		return;
	}
}
