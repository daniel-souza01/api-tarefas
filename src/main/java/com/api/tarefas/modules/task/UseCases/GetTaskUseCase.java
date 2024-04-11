package com.api.tarefas.modules.task.UseCases;

import com.api.tarefas.modules.task.dto.GetTaskResponseDTO;
import com.api.tarefas.modules.task.dto.TaskDetails;
import com.api.tarefas.modules.task.entities.Task;
import com.api.tarefas.modules.task.exceptions.TaskNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskUseCase {
	private final TaskRepository taskRepository;

	public GetTaskResponseDTO execute(String taskId) {
		Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

		TaskDetails taskDetails = new TaskDetails(
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

		return new GetTaskResponseDTO(taskDetails);
	}
}
