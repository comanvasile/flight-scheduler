package com.example.assignment.service;

import com.example.assignment.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    AirportDTO findById(Long id);

    List<AirportDTO> findAll();

    Long save(AirportDTO airportDTO);

    void delete(Long id);

    AirportDTO findByName(String name);
}
