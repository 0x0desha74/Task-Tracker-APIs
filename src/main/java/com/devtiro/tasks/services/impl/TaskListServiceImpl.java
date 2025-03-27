package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.repositories.TaskListRepository;
import com.devtiro.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService{

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<TaskList> getTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(null != taskList.getId())
            throw new IllegalArgumentException("Task list already has an Id!");

        if(null == taskList.getTitle() || taskList.getTitle().isBlank())
            throw new IllegalArgumentException("Title must be present");

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        if(null == taskList.getId())
            throw new IllegalArgumentException("Task list must have an Id");
        if(!Objects.equals(taskList.getId(),id))
            throw new IllegalArgumentException("Attempting to change task list Id, This is not permitted");

        var exsistingTaskList = taskListRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Task list not found"));
        exsistingTaskList.setTitle(taskList.getTitle());
        exsistingTaskList.setDescription(taskList.getDescription());
        exsistingTaskList.setUpdatedAt(LocalDateTime.now());

        return taskListRepository.save(exsistingTaskList);
    }
}
