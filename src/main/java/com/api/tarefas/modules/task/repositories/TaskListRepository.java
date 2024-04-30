package com.api.tarefas.modules.task.repositories;

import com.api.tarefas.modules.task.entities.TaskList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, String> {
	List<TaskList> getByUserId(String userId, Pageable pageable);

	long countByUserId(String userId);
}
