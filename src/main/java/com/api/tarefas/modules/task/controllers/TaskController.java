package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.UseCases.CreateTaskUseCase;
import com.api.tarefas.modules.task.dto.CreateTaskRequestDTO;
import com.api.tarefas.modules.task.dto.CreateTaskResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
	private final CreateTaskUseCase createTaskUseCase;

	@PostMapping
	public ResponseEntity<CreateTaskResponseDTO> createTask(@Valid @RequestBody CreateTaskRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
		CreateTaskResponseDTO result = this.createTaskUseCase.execute(body);

		var uri = uriComponentsBuilder.path("/tasks/{id}").buildAndExpand(result.taskId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}
}
