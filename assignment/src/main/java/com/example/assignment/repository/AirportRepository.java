package com.example.assignment.repository;

import com.example.assignment.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Airport findById(final Long id);
    Airport findByName(final String name);

}
