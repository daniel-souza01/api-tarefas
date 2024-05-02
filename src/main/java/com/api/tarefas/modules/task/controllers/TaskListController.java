package com.api.tarefas.modules.task.controllers;

import com.api.tarefas.modules.task.dto.*;
import com.api.tarefas.modules.task.services.TaskListService;
import com.api.tarefas.utils.Patterns;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/tasks/task-list")
@RequiredArgsConstructor
@Validated
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

	@PatchMapping("/{id}")
	public ResponseEntity<Void> updateTaskList(@PathVariable String id, @Valid @RequestBody UpdateTaskListRequestDTO body) {
		this.taskListService.updateTaskList(id, body);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeTaskList(@PathVariable String id) {
		this.taskListService.removeTaskList(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/list/{userId}")
	public ResponseEntity<ListTaskListsResponseDTO> listTaskLists(
			@PathVariable String userId,
			@RequestParam(name = "page", required = false, defaultValue = "0") @PositiveOrZero Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") @Positive Integer size,

			@RequestParam(name = "sort_by", required = false, defaultValue = "createdAt")
			@Pattern(regexp = Patterns.ListTaskListsSortBy, message = "Deve corresponder a title, removedAt, updatedAt ou createdAt") String sortBy,

			@RequestParam(name = "sort_dir", required = false, defaultValue = "asc")
			@Pattern(regexp = Patterns.SortDir, message = "Deve corresponder a asc ou desc") String sortDir
	) {
		var result = this.taskListService.listTaskLists(userId, new ListTaskListsQueryParams(page, size, sortBy, sortDir));

		return ResponseEntity.ok(result);
	}
}
