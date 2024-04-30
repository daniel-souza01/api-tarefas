package com.api.tarefas.modules.task.services;

import com.api.tarefas.modules.task.dto.*;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.TaskListAlreadyRemovedException;
import com.api.tarefas.modules.task.exceptions.TaskListNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

	public CreateTaskListResponseDTO createTaskList(CreateTaskListRequestDTO taskList) {
		User user = this.userService.getUserById(taskList.userId());

		TaskList newTaskList = new TaskList();
		newTaskList.setTitle(taskList.title());
		newTaskList.setUser(user);

		this.taskListRepository.save(newTaskList);

		return new CreateTaskListResponseDTO(newTaskList.getId());
	}

	public void updateTaskList(String taskListId, UpdateTaskListRequestDTO data) {
		TaskList taskList = this.getTaskListById(taskListId);
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

	public ListTaskListsResponseDTO listTaskLists(String userId, ListTaskListsQueryParams queryParams) {
		User user = this.userService.getUserById(userId);

		var direction = queryParams.sortDir().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

		var taskLists = this.taskListRepository
				.getByUserId(user.getId(), PageRequest.of(queryParams.page(), queryParams.size(), direction, queryParams.sortBy()))
				.stream().map(taskList -> new TaskListResponseDTO(
						taskList.getId(),
						taskList.getUser().getId(),
						taskList.getTitle(),
						taskList.getRemovedAt(),
						taskList.getUpdatedAt(),
						taskList.getCreatedAt()
				)).toList();

		var totalTaskLists = this.taskListRepository.countByUserId(user.getId());

		return new ListTaskListsResponseDTO(totalTaskLists, taskLists);
	}
}
