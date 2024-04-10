package com.api.tarefas.modules.task.repositories;

import com.api.tarefas.modules.task.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, String> {
}
