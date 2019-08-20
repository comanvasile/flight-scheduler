package com.example.assignment.dto;

import java.util.List;

public class AirlineDTO extends IdDTO {
    private String name;
    List<IdDTO> flights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IdDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<IdDTO> flights) {
        this.flights = flights;
    }
}
