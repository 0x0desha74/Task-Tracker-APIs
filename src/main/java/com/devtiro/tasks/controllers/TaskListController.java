package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.dto.TaskListDto;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.mapper.TaskListMapper;
import com.devtiro.tasks.services.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getTaskLists() {
        var taskListDtos = taskListService.getTaskLists()
                .stream().map(taskListMapper::toDto)
                .toList();

        return new ResponseEntity<>(taskListDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto model){

        var createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(model)
        ) ;
        return new ResponseEntity<>(taskListMapper.toDto(createdTaskList),HttpStatus.CREATED);
    }


}
