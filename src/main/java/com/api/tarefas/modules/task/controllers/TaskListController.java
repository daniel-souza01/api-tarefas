package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.UseCases.CreateTaskListUseCase;
import com.api.tarefas.modules.task.dto.CreateTaskListDTO;
import com.api.tarefas.modules.task.dto.CreateTaskListResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks/task-list")
@RequiredArgsConstructor
public class TaskListController {
	private final CreateTaskListUseCase createTaskListUseCase;

	@PostMapping
	public ResponseEntity<CreateTaskListResponseDTO> createTaskList(@Valid @RequestBody CreateTaskListDTO body, UriComponentsBuilder uriComponentsBuilder) {
		CreateTaskListResponseDTO result = this.createTaskListUseCase.execute(body);

		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}
