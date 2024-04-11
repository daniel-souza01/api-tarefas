package com.api.tarefas.modules.task.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CreateTaskRequestDTO(
		@NotEmpty(message = "O id do usuário é obrigatório")
		String userId,

		@NotEmpty(message = "O id da lista de tarefas é obrigatório")
		String taskListId,

		@NotBlank(message = "O título é obrigatório")
		String title,
		
		LocalDateTime date,

		@Pattern(regexp = "^true$|^false$", message = "Deve ser true ou false")
		String isFavorite
) {
}
