package com.api.tarefas.modules.task.services;

import com.api.tarefas.modules.task.dto.TaskListWithTasksDTO;
import com.api.tarefas.modules.task.dto.TaskResponseDTO;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {
	private final TaskListRepository taskListRepository;
	private final UserService userService;
	private final TaskService taskService;

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
}
