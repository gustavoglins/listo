package com.listo.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.listo.model.task.Task;
import com.listo.model.task.TaskPriority;
import com.listo.model.task.TaskStatus;
import com.listo.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.listo.model.task.Task}
 */

@JsonPropertyOrder({"id", "user", "title", "description", "status", "priority", "creationDate"})
public record TaskResponseDTO(

        @JsonProperty("id")
        Long id,

        @JsonProperty("user")
        User user,

        @JsonProperty("title")
        String title,

        @JsonProperty("description")
        String description,

        @JsonProperty("status")
        TaskStatus status,

        @JsonProperty("priority")
        TaskPriority priority,

        @JsonProperty("creationDate")
        LocalDateTime creationDate) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public TaskResponseDTO(Task task) {
        this(task.getId(), task.getUser(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getCreationDate());
    }
}
