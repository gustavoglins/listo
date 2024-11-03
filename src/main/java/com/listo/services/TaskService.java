package com.listo.services;

import com.listo.dto.task.TaskRequestDTO;
import com.listo.dto.task.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO create(TaskRequestDTO requestDTO);

    TaskResponseDTO update(TaskRequestDTO requestDTO);

    TaskResponseDTO findById(Long id);

    List<TaskResponseDTO> listAll();

    void delete(Long id);
}
