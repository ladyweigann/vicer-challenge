package com.challenge.viceri.controllers;

import com.challenge.viceri.Services.TaskService;
import com.challenge.viceri.entities.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseBody
    public List<Task> getAllPendingTasks(@RequestParam(required = false) Integer priorityParam) {
        return taskService.getAllPendingTasks(priorityParam);
    }
}
