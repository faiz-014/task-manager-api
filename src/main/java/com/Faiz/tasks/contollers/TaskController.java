package com.Faiz.tasks.contollers;

import com.Faiz.tasks.dtos.requestDtos.TaskRequest;
import com.Faiz.tasks.dtos.requestDtos.TaskUpdateRequest;
import com.Faiz.tasks.dtos.responseDtos.TaskResponse;
import com.Faiz.tasks.models.User;
import com.Faiz.tasks.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TaskRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(user, request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getAllTasks(user));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "taskId") Long taskId
    ) {
        return ResponseEntity.ok(taskService.getTaskById(user, taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "taskId") Long taskId,
            @RequestBody TaskUpdateRequest request
            ) {
        return ResponseEntity.ok(taskService.updateTask(user, taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "taskId") Long taskId
    ) {
        taskService.deleteTask(user, taskId);
        return ResponseEntity.noContent().build();
    }
}
