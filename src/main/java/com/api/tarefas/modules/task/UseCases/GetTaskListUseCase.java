package com.api.tarefas.modules.task.UseCases;

import com.api.tarefas.modules.task.dto.GetTaskListResponseDTO;
import com.api.tarefas.modules.task.dto.TaskListDetails;
import com.api.tarefas.modules.task.entities.TaskList;
import com.api.tarefas.modules.task.exceptions.TaskListNotFoundException;
import com.api.tarefas.modules.task.repositories.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTaskListUseCase {
	private final TaskListRepository taskListRepository;

	public GetTaskListResponseDTO execute(String taskListId) {
		TaskList taskList = this.taskListRepository.findById(taskListId).orElseThrow(() -> new TaskListNotFoundException(taskListId));

		TaskListDetails taskListDetails = new TaskListDetails(
				taskList.getId(),
				taskList.getUser().getId(),
				taskList.getTitle(),
				taskList.getRemovedAt(),
				taskList.getUpdatedAt(),
				taskList.getCreatedAt()
		);

		return new GetTaskListResponseDTO(taskListDetails);
	}
}
