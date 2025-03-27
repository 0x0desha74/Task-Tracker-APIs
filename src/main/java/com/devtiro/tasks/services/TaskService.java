package com.devtiro.tasks.services;

import com.devtiro.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> getTasks(UUID taskListId);
    Task createTask(UUID taskL, Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
    Task updateTask(UUID taskListId,UUID taskId,Task task);
}
