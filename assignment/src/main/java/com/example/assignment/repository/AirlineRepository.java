package com.example.assignment.repository;

import com.example.assignment.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,Long> {
    Airline findById(final Long id);
    Airline findByName(final String name);

}
