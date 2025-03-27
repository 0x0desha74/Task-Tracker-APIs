package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.mapper.TaskMapper;
import com.devtiro.tasks.services.impl.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TasksController {
    private TaskService taskService;
    private TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable UUID task_list_id)
    {
        var mappedTasks = taskService.getTasks(task_list_id)
                .stream().map(taskMapper::toDto)
                .toList();
        return new ResponseEntity<>(mappedTasks, HttpStatus.OK);
    }


}
