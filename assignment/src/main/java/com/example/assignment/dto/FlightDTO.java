package com.example.assignment.dto;

import java.sql.Time;

public class FlightDTO extends  IdDTO {
    private IdDTO source;
    private IdDTO destination;
    private IdDTO haltStation;
    private IdDTO airline;
    private Time departure;
    private Time arrival;
    private Time haltDuration;
    private Time delay;

    public IdDTO getSource() {
        return source;
    }

    public void setSource(IdDTO source) {
        this.source = source;
    }

    public IdDTO getDestination() {
        return destination;
    }

    public void setDestination(IdDTO destination) {
        this.destination = destination;
    }

    public IdDTO getHaltStation() {
        return haltStation;
    }

    public void setHaltStation(IdDTO haltStation) {
        this.haltStation = haltStation;
    }

    public IdDTO getAirline() {
        return airline;
    }

    public void setAirline(IdDTO airline) {
        this.airline = airline;
    }

    public Time getDeparture() {
        return departure;
    }

    public void setDeparture(Time departure) {
        this.departure = departure;
    }

    public Time getArrival() {
        return arrival;
    }

    public void setArrival(Time arrival) {
        this.arrival = arrival;
    }

    public Time getHaltDuration() {
        return haltDuration;
    }

    public void setHaltDuration(Time haltDuration) {
        this.haltDuration = haltDuration;
    }

    public Time getDelay() {
        return delay;
    }

    public void setDelay(Time delay) {
        this.delay = delay;
    }
}
