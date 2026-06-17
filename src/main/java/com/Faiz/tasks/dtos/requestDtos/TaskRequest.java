package com.Faiz.tasks.dtos.requestDtos;

import com.Faiz.tasks.models.TaskPriority;
import com.Faiz.tasks.models.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "title can't be blank")
        String title,
        String description,
        TaskPriority taskPriority,
        TaskStatus taskStatus,
        LocalDate deadline
) {
}
