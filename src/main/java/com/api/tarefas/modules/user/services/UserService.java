package com.api.tarefas.modules.user.services;

import com.api.tarefas.modules.user.Exceptions.UserNotFoundException;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public User getUserById(String userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
