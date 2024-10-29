package com.listo.services;

import com.listo.dto.user.UserRequestDTO;
import com.listo.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO requestDTO);

    UserResponseDTO update(UserRequestDTO requestDTO);

    UserResponseDTO findById(Long id);

    List<UserResponseDTO> listAll();

    void delete(Long id);
}
