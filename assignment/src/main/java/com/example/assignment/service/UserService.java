package com.example.assignment.service;

import com.example.assignment.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);

    List<UserDTO> findAll();

    Long save(UserDTO userDTO,String airport);

    void delete(Long id);

    UserDTO login(UserDTO userDTO);

    UserDTO findByUsername(String username);
}
