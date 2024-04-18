package com.api.tarefas.modules.task.repositories;

import com.api.tarefas.modules.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
	List<Task> findByTaskListId(String taskListId);
}
