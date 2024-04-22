package com.api.tarefas.modules.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UpdateTaskListRequestDTO(
		@NotEmpty(message = "Required")
		String taskListId,

		@NotBlank(message = "Required")
		String title
) {
}
