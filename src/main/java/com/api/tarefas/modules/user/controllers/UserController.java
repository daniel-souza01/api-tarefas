package com.api.tarefas.modules.user.controllers;

import com.api.tarefas.modules.user.dto.CreateUserRequestDTO;
import com.api.tarefas.modules.user.dto.CreateUserResponseDTO;
import com.api.tarefas.modules.user.dto.UserResponseDTO;
import com.api.tarefas.modules.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
		var result = this.userService.createUser(body);

		var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(result.userId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUser(@PathVariable String id) {
		var result = this.userService.getUserDetails(id);
		return ResponseEntity.ok(result);
	}
}
