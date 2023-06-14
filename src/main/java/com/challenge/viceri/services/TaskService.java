package com.challenge.viceri.services;

import com.challenge.viceri.entities.CreateTaskDTO;
import com.challenge.viceri.entities.Priority;
import com.challenge.viceri.entities.Task;
import com.challenge.viceri.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllPendingTasks(Integer priorityParam) {
        return taskRepository.getAllPendingTasks(priorityParam);
    }

    public Task createNewTask(CreateTaskDTO taskDTO) {
        Priority priority = Priority.fromValue(Priority.getPriority(taskDTO.getPriority()));

        return taskRepository.createTask(taskDTO.getDescription(), priority.getValue());
    }

}
