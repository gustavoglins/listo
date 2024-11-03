package com.listo.controller;

import com.listo.dto.task.TaskRequestDTO;
import com.listo.dto.task.TaskResponseDTO;
import com.listo.services.TaskService;
import com.listo.utils.CustomMediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/task")
@Tag(name = "Task Management", description = "Endpoints for managing tasks, including creating, updating, retrieving, and deleting task information.")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping(consumes = {CustomMediaType.APPLICATION_JSON},
            produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a new task",
            description = "Create a new task",
            tags = {"Task Management"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content()),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO requestDTO) {
        TaskResponseDTO createdTask = service.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping(consumes = {CustomMediaType.APPLICATION_JSON},
            produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a task",
            description = "Update a task",
            tags = {"Task Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content()),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<TaskResponseDTO> update(@RequestBody @Valid TaskRequestDTO requestDTO) {
        TaskResponseDTO updatedTask = service.update(requestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping(value = "/{id}",
            produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Find a task by ID",
            description = "Find a task by ID",
            tags = {"Task Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskResponseDTO.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO foundTask = service.findById(id);
        return ResponseEntity.ok(foundTask);
    }

    @GetMapping(produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "List all registered tasks",
            description = "List all registered tasks",
            tags = {"Task Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200",
                            content = @Content(
                                    mediaType = CustomMediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class))
                            )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<List<TaskResponseDTO>> listAll() {
        List<TaskResponseDTO> foundTask = service.listAll();
        return ResponseEntity.ok(foundTask);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a task by ID",
            description = "Delete a task by ID",
            tags = {"Task Management"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
