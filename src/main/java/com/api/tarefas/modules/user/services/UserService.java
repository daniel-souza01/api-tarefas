package com.api.tarefas.modules.user.services;

import com.api.tarefas.modules.user.exceptions.UserAlreadyExistsException;
import com.api.tarefas.modules.user.exceptions.UserNotFoundException;
import com.api.tarefas.modules.user.dto.CreateUserRequestDTO;
import com.api.tarefas.modules.user.dto.CreateUserResponseDTO;
import com.api.tarefas.modules.user.dto.UserResponseDTO;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	public User getUserById(String userId) {
		return this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	public Optional<User> getUserByEmail(String userEmail) {
		return this.userRepository.findByEmail(userEmail);
	}

	public UserResponseDTO getUserDetails(String userId) {
		User user = this.getUserById(userId);
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
	}

	public CreateUserResponseDTO createUser(CreateUserRequestDTO user) {
		var userWithSameEmail = this.getUserByEmail(user.email());
		if (userWithSameEmail.isPresent()) throw new UserAlreadyExistsException();

		User newUser = new User();
		newUser.setName(user.name());
		newUser.setEmail(user.email());

		var passwordHash = this.passwordEncoder.encode(user.password());
		newUser.setPassword(passwordHash);

		this.userRepository.save(newUser);

		return new CreateUserResponseDTO(newUser.getId());
	}
}
