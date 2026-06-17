package com.Faiz.tasks.repositories;

import com.Faiz.tasks.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long userId);
    Optional<Task> findByUserIdAndId(Long userId, Long id);
}
