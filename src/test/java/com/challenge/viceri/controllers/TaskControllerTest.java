package com.challenge.viceri.controllers;

import com.challenge.viceri.api.impl.TaskController;
import com.challenge.viceri.entities.Priority;
import com.challenge.viceri.entities.Status;
import com.challenge.viceri.entities.Task;
import com.challenge.viceri.services.TaskService;
import com.challenge.viceri.utils.TestUtils;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    private final EasyRandom random = TestUtils.RANDOM;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TaskController controller;

    @Mock
    private TaskService service;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeAttributes();
    }
//
//    @Test
//    void shouldReturnAListOfPendingTaskDTOWhenSuccess() throws Exception {
//        when(service.getAllPendingTasks(anyInt())).thenReturn(List.of(task));
//
//        this.mockMvc
//                .perform(get("/tasks"))
//                .andExpect(status().isOk());
//    }


    private void initializeAttributes() {
        task = new Task(1L, "test task", Priority.LOW, Status.PENDING);
    }
}