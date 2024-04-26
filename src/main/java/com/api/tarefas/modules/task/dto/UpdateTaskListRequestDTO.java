package com.api.tarefas.modules.task.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskListRequestDTO(
		@NotBlank(message = "Required")
		String title
) {
}
