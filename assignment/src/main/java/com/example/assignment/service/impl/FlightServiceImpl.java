package com.example.assignment.service.impl;

import com.example.assignment.dto.FlightDTO;
import com.example.assignment.entity.Flight;
import com.example.assignment.mapper.FlightMapper;
import com.example.assignment.repository.AirlineRepository;
import com.example.assignment.repository.AirportRepository;
import com.example.assignment.repository.FlightRepository;
import com.example.assignment.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository,FlightMapper flightMapper,AirlineRepository airlineRepository,AirportRepository airportRepository){
        this.airportRepository=airportRepository;
        this.airlineRepository=airlineRepository;
        this.flightRepository=flightRepository;
        this.flightMapper=flightMapper;
    }

    @Override
    public FlightDTO findById(Long id) {
        final Flight flight=flightRepository.findById(id);
        return flightMapper.toDTO(flight);
    }

    @Override
    public List<FlightDTO> findAll() {

        return flightRepository.findAll().stream().map(flightMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long save(FlightDTO flightDTO) {
        Flight flight;
        if(flightRepository.findById(flightDTO.getId())!=null){
            flight=flightRepository.findById(flightDTO.getId());
        }
        else{
            flight=new Flight();
            airlineRepository.findById(flightDTO.getAirline().getId()).getFlights().add(flight);
            airportRepository.findById(flightDTO.getSource().getId()).getFlights().add(flight);
        }

      /* airportRepository.findById(flightDTO.getDestination().getId()).getFlights().add(flight);
      if(!airportRepository.findById(flightDTO.getHaltStation().getId()).equals("none")){
           airportRepository.findById(flightDTO.getHaltStation().getId()).getFlights().add(flight);
      }*/

        flightMapper.toEntityUpdate(flight,flightDTO);
        return flightMapper.toDTO(flightRepository.save(flight)).getId();
        //flight.setDelay(new Time(300));
      //  flightRepository.save(flight);
      //  return null;
    }

    @Override
    public void delete(Long id) {
        flightRepository.delete(id);
    }

}
