package com.example.assignment.mapper;

import com.example.assignment.dto.AirlineDTO;
import com.example.assignment.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses=IdMapper.class)
public interface AirlineMapper {
    @Mapping(target = "flights",ignore = true)
    Airline toEntity(AirlineDTO airlineDTO);

    @Mapping(target = "flights",ignore = true)
    void toEntityUpdate(@MappingTarget Airline airline,AirlineDTO airlineDTO);

    AirlineDTO toDTO(Airline airline);


}
