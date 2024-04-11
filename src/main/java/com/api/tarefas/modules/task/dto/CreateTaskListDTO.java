package com.api.tarefas.modules.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateTaskListDTO(
		@NotEmpty(message = "O id do usuário é obrigatório")
		String userId,

		@NotBlank(message = "O título é obrigatório")
		String title
) {
}
