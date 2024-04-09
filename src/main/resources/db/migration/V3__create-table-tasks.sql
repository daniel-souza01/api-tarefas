CREATE TABLE tasks (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    task_list_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    date TIMESTAMP,
    is_favorite BOOLEAN NOT NULL DEFAULT false,
    completed_at TIMESTAMP,
    removed_at TIMESTAMP,
    updated_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT tasks_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT tasks_task_list_id_fkey FOREIGN KEY (task_list_id) REFERENCES task_list (id) ON DELETE RESTRICT ON UPDATE CASCADE
);