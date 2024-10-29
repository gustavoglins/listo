package com.listo.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link com.listo.model.User}
 */
public record UserRequestDTO(

        @NotBlank(message = "Name cannot be blank or empty")
        String name,

        @NotBlank(message = "Login cannot be blank or empty")
        String login,

        @NotBlank(message = "Password cannot be blank or empty")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}