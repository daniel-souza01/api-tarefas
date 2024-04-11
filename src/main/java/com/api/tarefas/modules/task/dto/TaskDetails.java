package com.api.tarefas.modules.task.dto;

import java.time.LocalDateTime;

public record TaskDetails(
		String id,
		String userId,
		String taskListId,
		String title,
		LocalDateTime date,
		Boolean isFavorite,
		LocalDateTime completedAt,
		LocalDateTime removedAt,
		LocalDateTime updatedAt,
		LocalDateTime createdAt
) {
}
