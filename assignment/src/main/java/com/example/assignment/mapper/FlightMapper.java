package com.example.assignment.mapper;

import com.example.assignment.dto.FlightDTO;
import com.example.assignment.entity.Airline;
import com.example.assignment.entity.Airport;
import com.example.assignment.entity.Flight;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@Mapper(uses=IdMapper.class)
public abstract class FlightMapper {

    @Autowired
    EntityManager entityManager;

    @Mapping(target = "source",ignore = true)
    @Mapping(target = "destination",ignore = true)
    @Mapping(target = "haltStation",ignore = true)
    @Mapping(target = "airline",ignore = true)
    public abstract Flight toEntity(FlightDTO flightDTO);

    @Mapping(target = "source",ignore = true)
    @Mapping(target = "destination",ignore = true)
    @Mapping(target = "haltStation",ignore = true)
    @Mapping(target = "airline",ignore = true)
    public abstract  void toEntityUpdate(@MappingTarget Flight flight,FlightDTO flightDTO);



    public abstract FlightDTO toDTO(Flight flight);

    @AfterMapping
    void toDoAfterMapping(@MappingTarget Flight flight,FlightDTO flightDTO){
        final Airport source=entityManager.find(Airport.class,flightDTO.getSource().getId());
        final Airport destination=entityManager.find(Airport.class,flightDTO.getDestination().getId());
        final Airport haltStation=entityManager.find(Airport.class,flightDTO.getHaltStation().getId());
        final Airline airline=entityManager.find(Airline.class,flightDTO.getAirline().getId());
        flight.setSource(source);
        flight.setAirline(airline);
        flight.setDestination(destination);
        flight.setHaltStation(haltStation);
    }
}
