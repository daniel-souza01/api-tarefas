package com.api.tarefas.modules.user.controllers;

import com.api.tarefas.modules.user.dto.CreateUserDTO;
import com.api.tarefas.modules.user.dto.CreateUserResponseDTO;
import com.api.tarefas.modules.user.dto.GetUserResponseDTO;
import com.api.tarefas.modules.user.useCases.CreateUserUseCase;
import com.api.tarefas.modules.user.useCases.GetUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final CreateUserUseCase createUserUseCase;
	private final GetUserUseCase getUserUseCase;

	@PostMapping
	public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserDTO body, UriComponentsBuilder uriComponentsBuilder) {
		CreateUserResponseDTO result = this.createUserUseCase.execute(body);

		var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(result.userId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetUserResponseDTO> getUser(@PathVariable String id) {
		GetUserResponseDTO result = this.getUserUseCase.execute(id);
		return ResponseEntity.ok(result);
	}
}
