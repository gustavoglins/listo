package com.listo.controller;

import com.listo.dto.user.UserRequestDTO;
import com.listo.dto.user.UserResponseDTO;
import com.listo.services.UserService;
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
@RequestMapping(value = "/api/user")
@Tag(name = "User Management", description = "Endpoints for managing users, including creating, updating, retrieving, and deleting user information.")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = {CustomMediaType.APPLICATION_JSON},
                 produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a new user",
            description = "Create a new user",
            tags = {"User Management"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content()),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO requestDTO) {
        UserResponseDTO createdUser = service.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping(consumes = {CustomMediaType.APPLICATION_JSON},
                produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a user",
            description = "Update a user",
            tags = {"User Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content()),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<UserResponseDTO> update(@RequestBody @Valid UserRequestDTO requestDTO) {
        UserResponseDTO createdUser = service.update(requestDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(value = "/{id}",
                produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "Find a user by ID",
            description = "Find a user by ID",
            tags = {"User Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public ResponseEntity<UserResponseDTO> findById(@PathVariable long id) {
        UserResponseDTO foundUser = service.findById(id);
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping(produces = {CustomMediaType.APPLICATION_JSON})
    @Operation(
            summary = "List all registered users",
            description = "List all registered users",
            tags = {"User Management"},
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200",
                            content = @Content(
                                    mediaType = CustomMediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class))
                            )),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "5001", content = @Content())
            }
    )
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        List<UserResponseDTO> foundUsers = service.listAll();
        return ResponseEntity.ok(foundUsers);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a user by ID",
            description = "Delete a user by ID",
            tags = {"User Management"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content()),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content()),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content()),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content()),
                    @ApiResponse(description = "Internal Server Error", responseCode = "501", content = @Content())
            }
    )
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
