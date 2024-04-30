package com.api.tarefas.modules.task.dto;

import java.util.List;

public record ListTaskListsResponseDTO(long totalItems, List<TaskListResponseDTO> items) {
}
