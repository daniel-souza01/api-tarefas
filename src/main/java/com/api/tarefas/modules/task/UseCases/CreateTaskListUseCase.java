package com.api.tarefas.modules.task.UseCases;

import com.api.tarefas.modules.task.dto.CreateTaskListDTO;
import com.api.tarefas.modules.task.dto.CreateTaskListResponseDTO;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.UserNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskListUseCase {
	private final UserRepository userRepository;
	private final TaskListRepository taskListRepository;

	public CreateTaskListResponseDTO execute(CreateTaskListDTO taskList) {
		User user = this.userRepository.findById(taskList.userId()).orElseThrow(() -> new UserNotFoundException(taskList.userId()));

		TaskList newTaskList = new TaskList();
		newTaskList.setTitle(taskList.title());
		newTaskList.setUser(user);

		this.taskListRepository.save(newTaskList);

		return new CreateTaskListResponseDTO(newTaskList.getId());
	}
}
