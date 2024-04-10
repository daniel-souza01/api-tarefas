package com.api.tarefas.modules.task.entities;

import com.api.tarefas.modules.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "task_list_id", nullable = false)
	private TaskList taskList;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true)
	private LocalDateTime date;

	@Column(nullable = false, name = "is_favorite")
	private Boolean isFavorite;

	@Column(nullable = true, name = "completed_at")
	private LocalDateTime completedAt;

	@Column(nullable = true, name = "removed_at")
	private LocalDateTime removedAt;

	@Column(nullable = false, name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(nullable = false, name = "created_at")
	private LocalDateTime createdAt;
}
