package com.api.tarefas.modules.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
	String id;
	String userId;
	String taskListId;
	String title;
	LocalDateTime date;
	Boolean isFavorite;
	LocalDateTime completedAt;
	LocalDateTime removedAt;
	LocalDateTime updatedAt;
	LocalDateTime createdAt;
}
