package com.Faiz.tasks.services;

import com.Faiz.tasks.dtos.requestDtos.TaskRequest;
import com.Faiz.tasks.dtos.requestDtos.TaskUpdateRequest;
import com.Faiz.tasks.dtos.responseDtos.TaskResponse;
import com.Faiz.tasks.models.User;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(User user, TaskRequest taskRequest);
    List<TaskResponse> getAllTasks(User user);
    TaskResponse getTaskById(User user, Long taskId);
    TaskResponse updateTask(User user, Long taskId, TaskUpdateRequest request);
    void deleteTask(User user, Long taskId);
}
