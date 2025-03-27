package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.dto.TaskDto;
import com.devtiro.tasks.mapper.TaskMapper;
import com.devtiro.tasks.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("{task_id}")
    public ResponseEntity<Optional<TaskDto>> getTask(@PathVariable UUID task_list_id, @PathVariable UUID task_id)
    {
        var taskDto = taskService.getTask(task_list_id,task_id).map(taskMapper::toDto);
        return new ResponseEntity<>(taskDto,HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<TaskDto> createPost(@PathVariable UUID task_list_id, @RequestBody TaskDto model)
    {
        var createdTask = taskService.createTask(task_list_id,taskMapper.fromDto(model));
        return new ResponseEntity<>(taskMapper.toDto(createdTask),HttpStatus.CREATED);
    }




}
