package com.api.tarefas.modules.user.useCases;

import com.api.tarefas.modules.user.Exceptions.UserNotFoundException;
import com.api.tarefas.modules.user.dto.GetUserResponseDTO;
import com.api.tarefas.modules.user.dto.UserDetails;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {
	private final UserRepository userRepository;

	public GetUserResponseDTO execute(String userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

		UserDetails userDetails = new UserDetails(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
		
		return new GetUserResponseDTO(userDetails);
	}
}
