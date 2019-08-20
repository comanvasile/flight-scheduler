package com.example.assignment.repository;

import com.example.assignment.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findById(final Long id);

}
