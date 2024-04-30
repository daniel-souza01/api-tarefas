package com.api.tarefas.modules.task.dto;

public record ListTaskListsQueryParams(
		Integer page,
		Integer size,
		String sortBy,
		String sortDir
) {
}
