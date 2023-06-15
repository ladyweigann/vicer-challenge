package com.challenge.viceri.services;

import com.challenge.viceri.entities.Priority;
import com.challenge.viceri.entities.Status;
import com.challenge.viceri.entities.Task;
import com.challenge.viceri.entities.TaskDTO;
import com.challenge.viceri.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository taskRepository;

    private Task pendingTask;
    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeAttributes();
    }

    @Test
    void shouldReturnAListOfAllPendingTasks() {
        when(taskRepository.getAllPendingTasks(anyInt()))
                .thenReturn(List.of(pendingTask));

        var response = service.getAllPendingTasks(1);

        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void shouldCreateNewTaskWhenSuccess() {
        when(taskRepository.createTask(any(), any()))
                .thenReturn(pendingTask);

        var response = service.createNewTask(taskDTO);

        assertNotNull(response);
        verify(taskRepository, times(1)).createTask(any(), any());
    }

    @Test
    void shouldupdateExistingTaskWhenSuccess() {
        when(taskRepository.updateTask(any(), any(), any()))
                .thenReturn(pendingTask);

        var response = service.updateExistingTask(1L, taskDTO);

        assertNotNull(response);
        verify(taskRepository, times(1))
                .updateTask(any(), any(), any());
        assertEquals(Status.PENDING, response.status());
    }

    @Test
    void shouldCompleteExistingTaskWhenSuccess() {
        service.completeExistingTask(1L);

        verify(taskRepository, times(1))
                .completeTask(any());
    }

    @Test
    void shouldDeleteExistingTaskWhenSuccess() {
        service.deleteExistingTask(1L);

        verify(taskRepository, times(1)).deleteTask(1L);
    }

    private void initializeAttributes() {
        pendingTask = new Task(1L, "test task", Priority.LOW, Status.PENDING);
        taskDTO = new TaskDTO("test task DTO", "alta");
    }
}