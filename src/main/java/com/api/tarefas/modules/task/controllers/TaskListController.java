package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.UseCases.CreateTaskListUseCase;
import com.api.tarefas.modules.task.UseCases.GetTaskListUseCase;
import com.api.tarefas.modules.task.dto.CreateTaskListDTO;
import com.api.tarefas.modules.task.dto.CreateTaskListResponseDTO;
import com.api.tarefas.modules.task.dto.GetTaskListResponseDTO;
import com.api.tarefas.modules.task.dto.TaskListWithTasksDTO;
import com.api.tarefas.modules.task.services.TaskListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tasks/task-list")
@RequiredArgsConstructor
public class TaskListController {
	private final CreateTaskListUseCase createTaskListUseCase;
	private final GetTaskListUseCase getTaskListUseCase;
	private final TaskListService taskListService;

	@PostMapping
	public ResponseEntity<CreateTaskListResponseDTO> createTaskList(@Valid @RequestBody CreateTaskListDTO body, UriComponentsBuilder uriComponentsBuilder) {
		CreateTaskListResponseDTO result = this.createTaskListUseCase.execute(body);

		var uri = uriComponentsBuilder.path("/tasks/task-list/{id}").buildAndExpand(result.taskListId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetTaskListResponseDTO> getTaskList(@PathVariable String id) {
		GetTaskListResponseDTO result = this.getTaskListUseCase.execute(id);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/all-with-tasks/{userId}")
	public ResponseEntity<List<TaskListWithTasksDTO>> getAllWithTasks(@PathVariable String userId) {
		var result = this.taskListService.getAllWithTasks(userId);
		return ResponseEntity.ok(result);
	}
}
