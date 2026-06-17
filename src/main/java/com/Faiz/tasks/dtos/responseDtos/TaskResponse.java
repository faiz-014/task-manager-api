package com.Faiz.tasks.dtos.responseDtos;

import com.Faiz.tasks.models.TaskPriority;
import com.Faiz.tasks.models.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskPriority taskPriority,
        TaskStatus taskStatus,
        LocalDate deadline,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
