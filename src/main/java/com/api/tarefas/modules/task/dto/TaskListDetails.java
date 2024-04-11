package com.api.tarefas.modules.task.dto;

import java.time.LocalDateTime;

public record TaskListDetails(
		String id,
		String userId,
		String title,
		LocalDateTime removedAt,
		LocalDateTime updatedAt,
		LocalDateTime createdAt
) {
}
