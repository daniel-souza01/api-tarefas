package com.api.tarefas.modules.task.dto;

import com.api.tarefas.utils.Patterns;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UpdateTaskRequestDTO(
		String title,

		@Pattern(regexp = Patterns.OptionalBoolean, message = "Deve ser true ou false")
		String isFavorite,

		LocalDateTime date,
		LocalDateTime completedAt
) {
}
