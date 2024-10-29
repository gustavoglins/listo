package com.listo.dto;

import com.listo.model.User;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link com.listo.model.User}
 */
public record UserDTO(
        Long id,

        @NotBlank(message = "Name cannot be blank or empty")
        String name,

        @NotBlank(message = "Login cannot be blank or empty")
        String login,

        @NotBlank(message = "Password cannot be blank or empty")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserDTO(User user){
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }
}