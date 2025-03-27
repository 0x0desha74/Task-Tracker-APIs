package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getTasks(UUID taskListId);
}
