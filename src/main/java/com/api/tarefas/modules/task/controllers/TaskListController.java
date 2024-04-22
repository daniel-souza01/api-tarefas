package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.dto.*;
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
	private final TaskListService taskListService;

	@PostMapping
	public ResponseEntity<CreateTaskListResponseDTO> createTaskList(@Valid @RequestBody CreateTaskListRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
		var result = this.taskListService.createTaskList(body);

		var uri = uriComponentsBuilder.path("/tasks/task-list/{id}").buildAndExpand(result.taskListId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskListResponseDTO> getTaskList(@PathVariable String id) {
		var result = this.taskListService.getTaskListDetails(id);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/all-with-tasks/{userId}")
	public ResponseEntity<List<TaskListWithTasksDTO>> getAllWithTasks(@PathVariable String userId) {
		var result = this.taskListService.getAllWithTasks(userId);
		return ResponseEntity.ok(result);
	}

	@PatchMapping
	public ResponseEntity<Void> updateTaskList(@Valid @RequestBody UpdateTaskListRequestDTO body) {
		this.taskListService.updateTaskList(body);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeTaskList(@PathVariable String id) {
		this.taskListService.removeTaskList(id);
		return ResponseEntity.ok().build();
	}
}
