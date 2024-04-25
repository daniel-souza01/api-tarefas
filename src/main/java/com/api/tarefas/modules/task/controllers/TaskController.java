package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.dto.CreateTaskRequestDTO;
import com.api.tarefas.modules.task.dto.CreateTaskResponseDTO;
import com.api.tarefas.modules.task.dto.TaskResponseDTO;
import com.api.tarefas.modules.task.dto.UpdateTaskRequestDTO;
import com.api.tarefas.modules.task.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@PostMapping
	public ResponseEntity<CreateTaskResponseDTO> createTask(@Valid @RequestBody CreateTaskRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
		var result = this.taskService.createTask(body);

		var uri = uriComponentsBuilder.path("/tasks/{id}").buildAndExpand(result.taskId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTask(@PathVariable String id) {
		var result = this.taskService.getTaskDetails(id);
		return ResponseEntity.ok(result);
	}

	@PutMapping
	public ResponseEntity<Void> updateTask(@Valid @RequestBody UpdateTaskRequestDTO body) {
		this.taskService.updateTask(body);
		return ResponseEntity.ok().build();
	}
}
