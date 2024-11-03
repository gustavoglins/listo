package com.listo.controller;

import com.listo.dto.task.TaskRequestDTO;
import com.listo.dto.task.TaskResponseDTO;
import com.listo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO requestDTO) {
        TaskResponseDTO createdTask = service.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping
    public ResponseEntity<TaskResponseDTO> update(@RequestBody @Valid TaskRequestDTO requestDTO) {
        TaskResponseDTO updatedTask = service.update(requestDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        TaskResponseDTO foundTask = service.findById(id);
        return ResponseEntity.ok(foundTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listAll() {
        List<TaskResponseDTO> foundTask = service.listAll();
        return ResponseEntity.ok(foundTask);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
