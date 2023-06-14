package com.challenge.viceri.api.impl;

import com.challenge.viceri.api.TaskApi;
import com.challenge.viceri.entities.TaskDTO;
import com.challenge.viceri.entities.Task;
import com.challenge.viceri.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController implements TaskApi {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<Task>> getPendingTasks(Integer priorityParam) {
        List<Task> allPendingTasks = taskService.getAllPendingTasks(priorityParam);

        return allPendingTasks.stream()
                .findFirst()
                .map(task -> ResponseEntity.ok(allPendingTasks))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<Task> createTask(TaskDTO taskDTO) {
        Task newTask = taskService.createNewTask(taskDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newTask.id()).toUri();

        return ResponseEntity.created(uri).body(newTask);
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, TaskDTO taskDTO) {
        Task updatedTask = taskService.updateExistingTask(id, taskDTO);

        if(updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }

        return ResponseEntity.notFound().build();

    }
}
