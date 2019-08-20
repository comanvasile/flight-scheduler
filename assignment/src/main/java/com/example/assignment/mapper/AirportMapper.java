package com.example.assignment.mapper;

import com.example.assignment.dto.AirportDTO;
import com.example.assignment.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses=IdMapper.class)
public interface AirportMapper {
    @Mapping(target = "users",ignore = true)
    @Mapping(target = "flights",ignore = true)
    Airport toEntity(AirportDTO airportDTO);

    @Mapping(target = "users",ignore = true)
    @Mapping(target = "flights",ignore = true)
    void toEntityUpdate(@MappingTarget Airport airport,AirportDTO airportDTO);

    AirportDTO toDTO(Airport airport);
}
