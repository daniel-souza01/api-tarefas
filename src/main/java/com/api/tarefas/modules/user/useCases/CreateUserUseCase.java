package com.api.tarefas.modules.user.useCases;

import com.api.tarefas.modules.user.Exceptions.UserAlreadyExistsException;
import com.api.tarefas.modules.user.dto.CreateUserDTO;
import com.api.tarefas.modules.user.dto.CreateUserResponseDTO;
import com.api.tarefas.modules.user.entities.User;
import com.api.tarefas.modules.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public CreateUserResponseDTO execute(CreateUserDTO user) {
		Optional<User> userWithSameEmail = this.userRepository.findByEmail(user.email());
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
