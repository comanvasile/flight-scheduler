package com.example.assignment.model;

public class FlightModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String source;
    private String departure;
    private String destination;
    private String arrival;
    private String haltStation;
    private String haltDuration;
    private String airline;
    private String delay;

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getHaltDuration() {
        return haltDuration;
    }

    public void setHaltDuration(String haltDuration) {
        this.haltDuration = haltDuration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



    public String getHaltStation() {
        return haltStation;
    }

    public void setHaltStation(String haltStation) {
        this.haltStation = haltStation;
    }


}
