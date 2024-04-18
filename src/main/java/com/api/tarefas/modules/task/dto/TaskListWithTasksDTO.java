package com.api.tarefas.modules.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskListWithTasksDTO extends TaskListResponseDTO {
	List<TaskResponseDTO> tasks;

	public TaskListWithTasksDTO(String id, String userId, String title, LocalDateTime removedAt, LocalDateTime updatedAt, LocalDateTime createdAt, List<TaskResponseDTO> tasks) {
		super(id, userId, title, removedAt, updatedAt, createdAt);
		this.tasks = tasks;
	}
}
