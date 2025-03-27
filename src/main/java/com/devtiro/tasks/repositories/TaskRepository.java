package com.devtiro.tasks.repositories;

import com.devtiro.tasks.domain.entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);

    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);
//    @Transactional
//    @Modifying
    void deleteByTaskListIdAndId(UUID taskListId,UUID taskId);

}
