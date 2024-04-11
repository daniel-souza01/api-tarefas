package com.api.tarefas.modules.user.dto;

import java.time.LocalDateTime;

public record UserDetails(
		String id,
		String name,
		String email,
		LocalDateTime createdAt
) {
}
