package com.listo.services;

import com.listo.dto.user.UserRequestDTO;
import com.listo.dto.user.UserResponseDTO;
import com.listo.model.User;
import com.listo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        User createdUser = repository.save(new User(requestDTO));
        return new UserResponseDTO(createdUser);
    }

    @Override
    public UserResponseDTO update(UserRequestDTO requestDTO) {
        Optional<User> optionalUser = repository.findById(requestDTO.id());
        if (optionalUser.isPresent()) {
            User selectedUser = optionalUser.get();

            selectedUser.setName(requestDTO.name());
            selectedUser.setLogin(requestDTO.login());
            selectedUser.setPassword(requestDTO.password());

            return new UserResponseDTO(repository.save(selectedUser));
        } else {
            throw new RuntimeException("User not found"); //TODO: create a custom exception for this case
        }
    }

    @Override
    public UserResponseDTO findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User selectedUser = optionalUser.get();
            return new UserResponseDTO(selectedUser);
        } else {
            throw new RuntimeException("User not found"); //TODO: create a custom exception for this case
        }
    }

    @Override
    public List<UserResponseDTO> listAll() {
        List<User> userList = repository.findAll();
        return userList.stream()
                .map(UserResponseDTO::new)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("User not found"); //TODO: create a custom exception for this case
        }
    }
}
