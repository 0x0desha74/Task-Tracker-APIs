package com.devtiro.tasks.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "task_lists")
public class TaskList {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(nullable = false,updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;


}
