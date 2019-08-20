package com.example.assignment.mapper;

import com.example.assignment.dto.IdDTO;
import com.example.assignment.entity.Airline;
import com.example.assignment.entity.Airport;
import com.example.assignment.entity.Flight;
import com.example.assignment.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface IdMapper {

    IdDTO userToIdDTO(User user);

    IdDTO airlineToIdDTO(Airline airline);

    IdDTO airportToIdDTO(Airport airport);

    IdDTO flightToIdDTO(Flight flight);
}
