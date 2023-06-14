package com.challenge.viceri.services;

import com.challenge.viceri.entities.TaskDTO;
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

    public Task createNewTask(TaskDTO taskDTO) {
        Priority priority = Priority.fromValue(Priority.getPriority(taskDTO.getPriority()));

        return taskRepository.createTask(taskDTO.getDescription(), priority.getValue());
    }

    public Task updateExistingTask(Long id, TaskDTO taskDTO) {
        Priority priority = Priority.fromValue(Priority.getPriority(taskDTO.getPriority()));

        return taskRepository.updateTask(id, taskDTO.getDescription(), priority.getValue());
    }

    public void completeExistingTask(Long id) {
        taskRepository.completeTask(id);
    }

    public void deleteExistingTask(Long id) {
        taskRepository.deleteTask(id);
    }

}
