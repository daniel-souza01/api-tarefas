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
public class TaskListResponseDTO {
	String id;
	String userId;
	String title;
	LocalDateTime removedAt;
	LocalDateTime updatedAt;
	LocalDateTime createdAt;
}
