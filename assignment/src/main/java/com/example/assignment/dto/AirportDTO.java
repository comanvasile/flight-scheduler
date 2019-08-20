package com.example.assignment.dto;

import java.util.List;

public class AirportDTO extends IdDTO {
    private String name;
    private List<IdDTO> users;
    private List<IdDTO> flights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IdDTO> getUsers() {
        return users;
    }

    public void setUsers(List<IdDTO> users) {
        this.users = users;
    }

    public List<IdDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<IdDTO> flights) {
        this.flights = flights;
    }
}
