package com.Faiz.tasks.services.impl;

import com.Faiz.tasks.dtos.requestDtos.TaskRequest;
import com.Faiz.tasks.dtos.requestDtos.TaskUpdateRequest;
import com.Faiz.tasks.dtos.responseDtos.TaskResponse;
import com.Faiz.tasks.exceptions.TaskNotFoundException;
import com.Faiz.tasks.mappers.TaskMapper;
import com.Faiz.tasks.models.Task;
import com.Faiz.tasks.models.User;
import com.Faiz.tasks.repositories.TaskRepository;
import com.Faiz.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(User user, TaskRequest taskRequest) {
        Task task = taskMapper.fromDto(user, taskRequest);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getAllTasks(User user) {
        return taskRepository.findAllByUserId(user.getId())
                .stream()
                .map(task->taskMapper.toDto(task))
                .toList();
    }

    @Override
    public TaskResponse getTaskById(User user, Long taskId) {
        return taskRepository.findByUserIdAndId(user.getId(), taskId)
                .map(task->taskMapper.toDto(task))
                .orElseThrow(()->new TaskNotFoundException("Task not found: "+taskId));
    }

    @Override
    public TaskResponse updateTask(User user, Long taskId, TaskUpdateRequest request) {
        Task task = taskRepository.findByUserIdAndId(user.getId(), taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found: " + taskId));

        if(request.title() != null) task.setTitle(request.title());
        if(request.description() != null) task.setDescription(request.description());
        if(request.taskPriority() != null) task.setTaskPriority(request.taskPriority());
        if(request.taskStatus() != null) task.setTaskStatus(request.taskStatus());
        if(request.deadline() != null) task.setDeadline(request.deadline());

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(User user, Long taskId) {
        Task task = taskRepository.findByUserIdAndId(user.getId(), taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found: " + taskId));
        taskRepository.delete(task);
    }
}
