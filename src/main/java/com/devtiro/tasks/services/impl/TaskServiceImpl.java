package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.TaskPriority;
import com.devtiro.tasks.domain.entities.TaskStatus;
import com.devtiro.tasks.repositories.TaskListRepository;
import com.devtiro.tasks.repositories.TaskRepository;
import com.devtiro.tasks.services.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> getTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (null != task.getId())
            throw new IllegalArgumentException("Task already has an Id");
        if (null == task.getTitle() || task.getTitle().isBlank())
            throw new IllegalArgumentException("Task have a title");

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() ->
                new IllegalArgumentException("Invalid task list Id provided"));

        LocalDateTime now = LocalDateTime.now();
        Task createdTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(createdTask);

    }

    @Transactional
    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {

        if (null == task.getId())
            throw new IllegalArgumentException("Task must have an Id");

        if (!Objects.equals(taskId, task.getId()))
            throw new IllegalArgumentException("Attempting to change task Id, this is not permitted");

        if(null == task.getPriority())
            throw new IllegalArgumentException("Task must have a valid priority");

        if(null == task.getStatus())
            throw new IllegalArgumentException("Task must have a valid status");

        var existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(existingTask);

    }
    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
