package com.api.tarefas.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateUserDTO(
		@NotBlank(message = "O nome é obrigatório")
		String name,

		@Email(message = "O e-mail deve ser válido")
		String email,

		@Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
		String password
) {
}
