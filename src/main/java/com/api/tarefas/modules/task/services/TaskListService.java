package com.api.tarefas.modules.task.services;

import com.api.tarefas.modules.task.dto.*;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.TaskListAlreadyRemovedException;
import com.api.tarefas.modules.task.exceptions.TaskListNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListService {
	@Autowired
	private TaskListRepository taskListRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	public TaskList getTaskListById(String taskListId) {
		return this.taskListRepository.findById(taskListId).orElseThrow(() -> new TaskListNotFoundException(taskListId));
	}

	public TaskListResponseDTO getTaskListDetails(String taskListId) {
		TaskList taskList = this.getTaskListById(taskListId);
		return new TaskListResponseDTO(
				taskList.getId(),
				taskList.getUser().getId(),
				taskList.getTitle(),
				taskList.getRemovedAt(),
				taskList.getUpdatedAt(),
				taskList.getCreatedAt()
		);
	}

	public List<TaskListWithTasksDTO> getAllWithTasks(String userId) {
		User user = this.userService.getUserById(userId);

		return this.taskListRepository.findByUserId(user.getId()).stream().map(taskList -> {
			List<TaskResponseDTO> tasks = this.taskService.getAllByTaskListId(taskList.getId()).stream().map(task -> {
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
			}).toList();

			return new TaskListWithTasksDTO(
					taskList.getId(),
					taskList.getUser().getId(),
					taskList.getTitle(),
					taskList.getRemovedAt(),
					taskList.getUpdatedAt(),
					taskList.getCreatedAt(),
					tasks
			);
		}).toList();
	}

	public CreateTaskListResponseDTO createTaskList(CreateTaskListRequestDTO taskList) {
		User user = this.userService.getUserById(taskList.userId());

		TaskList newTaskList = new TaskList();
		newTaskList.setTitle(taskList.title());
		newTaskList.setUser(user);

		this.taskListRepository.save(newTaskList);

		return new CreateTaskListResponseDTO(newTaskList.getId());
	}

	public void updateTaskList(UpdateTaskListRequestDTO data) {
		TaskList taskList = this.getTaskListById(data.taskListId());
		taskList.setTitle(data.title());
		this.taskListRepository.save(taskList);
		return;
	}

	public void removeTaskList(String taskListId) {
		TaskList taskList = this.getTaskListById(taskListId);

		if (taskList.getRemovedAt() != null) {
			throw new TaskListAlreadyRemovedException();
		}

		taskList.setRemovedAt(LocalDateTime.now());
		this.taskListRepository.save(taskList);
		return;
	}
}
