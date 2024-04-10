package com.api.tarefas.modules.task.repositories;

import com.api.tarefas.modules.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
