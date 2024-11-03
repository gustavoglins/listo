package com.listo.services;

import com.listo.dto.user.UserRequestDTO;
import com.listo.dto.user.UserResponseDTO;
import com.listo.exceptions.UserNotFoundException;
import com.listo.model.user.User;
import com.listo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO requestDTO) {
        User newUser = new User(requestDTO);
        return new UserResponseDTO(repository.save(newUser));
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
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public UserResponseDTO findById(Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            return new UserResponseDTO(optionalUser.get());
        } else {
            throw new UserNotFoundException("User not found");
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
            throw new UserNotFoundException("User not found");
        }
    }
}
