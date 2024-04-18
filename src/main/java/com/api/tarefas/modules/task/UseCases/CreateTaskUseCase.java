package com.api.tarefas.modules.task.UseCases;

import com.api.tarefas.modules.task.dto.CreateTaskRequestDTO;
import com.api.tarefas.modules.task.dto.CreateTaskResponseDTO;
import com.api.tarefas.modules.task.entities.Task;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.TaskListNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import com.api.tarefas.modules.task.repositories.TaskRepository;
import com.api.tarefas.modules.user.exceptions.UserNotFoundException;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase {
	private final UserRepository userRepository;
	private final TaskListRepository taskListRepository;
	private final TaskRepository taskRepository;

	public CreateTaskResponseDTO execute(CreateTaskRequestDTO task) {
		User user = this.userRepository.findById(task.userId()).orElseThrow(() -> new UserNotFoundException(task.userId()));

		TaskList taskList = this.taskListRepository.findById(task.taskListId()).orElseThrow(() -> new TaskListNotFoundException(task.taskListId()));

		Task newTask = new Task();
		newTask.setUser(user);
		newTask.setTaskList(taskList);
		newTask.setTitle(task.title());
		newTask.setDate(task.date());
		newTask.setIsFavorite(Boolean.valueOf(task.isFavorite()));

		this.taskRepository.save(newTask);

		return new CreateTaskResponseDTO(newTask.getId());
	}
}
