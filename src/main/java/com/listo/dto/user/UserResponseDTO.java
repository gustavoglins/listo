package com.listo.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.listo.model.user.User;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@JsonPropertyOrder({"id", "name", "login", "password"})
public record UserResponseDTO(

        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("login")
        String login,

        @JsonProperty("password")
        String password) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword());
    }
}
