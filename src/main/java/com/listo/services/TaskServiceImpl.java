package com.listo.services;

import com.listo.dto.task.TaskRequestDTO;
import com.listo.dto.task.TaskResponseDTO;
import com.listo.model.task.Task;
import com.listo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO requestDTO) {
        LocalDateTime creationDate = LocalDateTime.now();
        Task newTask = new Task(requestDTO, creationDate);
        return new TaskResponseDTO(repository.save(newTask));
    }

    @Override
    public TaskResponseDTO update(TaskRequestDTO requestDTO) {
        Optional<Task> optionalTask = repository.findById(requestDTO.id());
        if (optionalTask.isPresent()) {
            Task selectedTask = optionalTask.get();

            selectedTask.setTitle(requestDTO.title());
            selectedTask.setDescription(requestDTO.description());
            selectedTask.setStatus(requestDTO.status());
            selectedTask.setPriority(requestDTO.priority());

            return new TaskResponseDTO(repository.save(selectedTask));
        } else {
            throw new RuntimeException("Task not found"); //TODO: create a custom exception for this case
        }
    }

    @Override
    public TaskResponseDTO findById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            return new TaskResponseDTO(optionalTask.get());
        } else {
            throw new RuntimeException("Task not found"); //TODO: create a custom exception for this case
        }
    }

    @Override
    public List<TaskResponseDTO> listAll() {
        List<Task> taskList = repository.findAll();
        return taskList.stream()
                .map(TaskResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Task not found"); //TODO: create a custom exception for this case
        }
    }
}
