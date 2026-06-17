package com.Faiz.tasks.dtos.requestDtos;

import com.Faiz.tasks.models.TaskPriority;
import com.Faiz.tasks.models.TaskStatus;

import java.time.LocalDate;

public record TaskUpdateRequest(
        String title,
        String description,
        TaskPriority taskPriority,
        TaskStatus taskStatus,
        LocalDate deadline
) {
}
