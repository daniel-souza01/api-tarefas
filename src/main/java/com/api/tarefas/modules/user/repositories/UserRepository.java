package com.api.tarefas.modules.user.repositories;

import com.api.tarefas.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
