package com.api.tarefas.modules.task.entities;

import com.api.tarefas.modules.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskList {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private String title;

	@Column(nullable = true, name = "removed_at")
	private LocalDateTime removedAt;

	@Column(nullable = false, name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(nullable = false, name = "created_at")
	private LocalDateTime createdAt;
}
