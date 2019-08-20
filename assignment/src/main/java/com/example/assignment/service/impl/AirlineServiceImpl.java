package com.example.assignment.service.impl;

import com.example.assignment.dto.AirlineDTO;
import com.example.assignment.entity.Airline;
import com.example.assignment.mapper.AirlineMapper;
import com.example.assignment.repository.AirlineRepository;
import com.example.assignment.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;
    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository,AirlineMapper airlineMapper){
        this.airlineRepository=airlineRepository;
        this.airlineMapper=airlineMapper;
    }

    @Override
    public AirlineDTO findById(Long id) {
        final Airline airline=airlineRepository.findById(id);
        return airlineMapper.toDTO(airline);
    }

    @Override
    public List<AirlineDTO> findAll() {
        return airlineRepository.findAll().stream().map(airlineMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long save(AirlineDTO airlineDTO) {

        Airline airline=new Airline();
        if(airlineRepository.findById(airlineDTO.getId())!=null){
            airline=airlineRepository.findById(airlineDTO.getId());
        }
        else{
            airline=new Airline();
        }
        airlineMapper.toEntityUpdate(airline,airlineDTO);
        return airlineMapper.toDTO(airlineRepository.save(airline)).getId();
    }

    @Override
    public void delete(Long id) {
        airlineRepository.delete(id);
    }

    @Override
    public AirlineDTO findByName(String name) {
        final Airline airline=airlineRepository.findByName(name);
        return airlineMapper.toDTO(airline);
    }
}
