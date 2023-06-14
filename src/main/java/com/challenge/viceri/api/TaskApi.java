package com.challenge.viceri.api;

import com.challenge.viceri.entities.TaskDTO;
import com.challenge.viceri.entities.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "tasks", description = "TO-DO API")
@RequestMapping(value = "/tasks", produces = {APPLICATION_JSON_VALUE})
public interface TaskApi {

    @Operation(
            summary = "Get all tasks that are with status pending, and optionally you can filter by its priority, using a Query Param 1 - Low, 2 - Medium and 3 high",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Task.class))
                            }),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE)
                            })
            }
    )
    @GetMapping
    ResponseEntity<List<Task>> getPendingTasks(@RequestParam(required = false) Integer priorityParam);

    @Operation(
            summary = "Create a new task by passing description and priority (alta, média, baixa)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Task.class))
                            }),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE)
                            })
            }
    )
    ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO);

    @Operation(
            summary = "Update an existing task description and priority by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Task.class))
                            }),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = {
                                    @Content(mediaType = APPLICATION_JSON_VALUE)
                            })
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long id, @RequestBody TaskDTO taskDTO);
}
