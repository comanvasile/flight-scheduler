package com.example.assignment.service;

import com.example.assignment.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO findById(Long id);

    List<FlightDTO> findAll();

    Long save(FlightDTO flightDTO);

    void delete(Long id);

}
