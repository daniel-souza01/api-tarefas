package com.api.tarefas.modules.user.controllers;

import com.api.tarefas.modules.user.dto.CreateUserDTO;
import com.api.tarefas.modules.user.dto.CreateUserResponseDTO;
import com.api.tarefas.modules.user.useCases.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final CreateUserUseCase createUserUseCase;

	@PostMapping
	public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserDTO body, UriComponentsBuilder uriComponentsBuilder) {
		CreateUserResponseDTO result = this.createUserUseCase.execute(body);

		var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(result.userId()).toUri();

		return ResponseEntity.created(uri).body(result);
	}
}
