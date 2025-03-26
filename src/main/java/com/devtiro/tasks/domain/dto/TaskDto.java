package com.devtiro.tasks.domain.dto;

import com.devtiro.tasks.domain.entities.TaskPriority;
import com.devtiro.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

//record => for immutable data class
//records provides getter, allArgsConstructor, ToString, immutability
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueTime,
        TaskPriority priority,
        TaskStatus status

) {




}
