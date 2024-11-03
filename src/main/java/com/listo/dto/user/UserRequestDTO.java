package com.listo.dto.user;

import com.listo.model.user.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserRequestDTO(

        Long id,

        @NotBlank(message = "Name cannot be blank or empty")
        String name,

        @NotBlank(message = "Login cannot be blank or empty")
        String login,

        @NotBlank(message = "Password cannot be blank or empty")
        @Min(8)
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
}