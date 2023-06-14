package com.challenge.viceri.api;

import com.challenge.viceri.entities.Task;
import com.challenge.viceri.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController implements TaskApi{
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public List<Task> getAllPendingTasks(Integer priorityParam) {
        return taskService.getAllPendingTasks(priorityParam);
    }
}
