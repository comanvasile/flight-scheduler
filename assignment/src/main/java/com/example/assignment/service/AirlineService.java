package com.example.assignment.service;

import com.example.assignment.dto.AirlineDTO;

import java.util.List;

public interface AirlineService {
    AirlineDTO findById(Long id);

    List<AirlineDTO> findAll();

    Long save(AirlineDTO airlineDTO);

    void delete(Long id);

    AirlineDTO findByName(String name);

}
