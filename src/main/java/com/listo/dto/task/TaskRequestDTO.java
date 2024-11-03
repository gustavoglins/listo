package com.listo.dto.task;

import com.listo.model.task.TaskPriority;
import com.listo.model.task.TaskStatus;
import com.listo.model.user.User;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;


/**
 * DTO for {@link com.listo.model.task.Task}
 */

public record TaskRequestDTO(

        @NotBlank(message = "Task user cannot be blank or empty")
        User user,

        @NotBlank(message = "Task title cannot be blank or empty")
        String title,

        @NotBlank(message = "Task description cannot be blank or empty")
        String description,

        @NotBlank(message = "Task status cannot be blank or empty")
        TaskStatus status,

        @NotBlank(message = "Task priority cannot be black or empty")
        TaskPriority priority) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}
