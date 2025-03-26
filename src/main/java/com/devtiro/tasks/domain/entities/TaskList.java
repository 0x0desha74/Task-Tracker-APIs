package com.devtiro.tasks.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    //remove => when we delete a task list, included tasks will be automatically deleted
    //persist => when we save a task list , included tasks will be automatically saved
    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Task> tasks;

}
