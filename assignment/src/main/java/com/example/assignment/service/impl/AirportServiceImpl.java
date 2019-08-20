package com.example.assignment.service.impl;

import com.example.assignment.dto.AirportDTO;
import com.example.assignment.entity.Airport;
import com.example.assignment.mapper.AirportMapper;
import com.example.assignment.repository.AirportRepository;
import com.example.assignment.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {
    private  final AirportRepository airportRepository;
    private final AirportMapper airportMapper;
    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository,AirportMapper airportMapper){
        this.airportRepository=airportRepository;
        this.airportMapper=airportMapper;
    }
    @Override
    public AirportDTO findById(Long id) {
        final Airport airport=airportRepository.findById(id);
        return airportMapper.toDTO(airport);
    }

    @Override
    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream().map(airportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long save(AirportDTO airportDTO) {

        Airport airport;
        if(airportRepository.findById(airportDTO.getId())!=null){
            airport=airportRepository.findById(airportDTO.getId());
        }
        else{
            airport=new Airport();
        }
        airportMapper.toEntityUpdate(airport,airportDTO);
        return airportMapper.toDTO(airportRepository.save(airport)).getId();
    }

    @Override
    public void delete(Long id) {
        airportRepository.delete(id);
    }

    @Override
    public AirportDTO findByName(String name) {
        final Airport airport=airportRepository.findByName(name);
        return airportMapper.toDTO(airport);
    }
}
