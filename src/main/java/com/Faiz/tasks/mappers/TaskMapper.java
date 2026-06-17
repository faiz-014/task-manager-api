package com.Faiz.tasks.mappers;

import com.Faiz.tasks.dtos.requestDtos.TaskRequest;
import com.Faiz.tasks.dtos.responseDtos.TaskResponse;
import com.Faiz.tasks.models.Task;
import com.Faiz.tasks.models.TaskPriority;
import com.Faiz.tasks.models.TaskStatus;
import com.Faiz.tasks.models.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task fromDto(User user, TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setTaskPriority(request.taskPriority() != null ? request.taskPriority() : TaskPriority.MEDIUM);
        task.setTaskStatus(request.taskStatus() != null ? request.taskStatus() : TaskStatus.TODO);
        task.setDeadline(request.deadline());
        task.setUser(user);

        return task;
    }

    public TaskResponse toDto(Task task) {
        TaskResponse response = new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTaskPriority(),
                task.getTaskStatus(),
                task.getDeadline(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
        return response;
    }
}
