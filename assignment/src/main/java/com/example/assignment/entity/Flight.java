package com.example.assignment.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity(name="ps_flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="source_id")
    private Airport source;
    @ManyToOne
    @JoinColumn(name="destination_id")
    private Airport destination;
    @ManyToOne
    @JoinColumn(name="haltStation_id")
    private Airport haltStation;
    @ManyToOne
    @JoinColumn(name="airline_id")
    private Airline airline;
    private Time departure;
    private Time arrival;

    public Time getDelay() {
        return delay;
    }

    public void setDelay(Time delay) {
        this.delay = delay;
    }

    private Time haltDuration;
    private Time delay;

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getSource() {
        return source;
    }

    public void setSource(Airport source) {
        this.source = source;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Airport getHaltStation() {
        return haltStation;
    }

    public void setHaltStation(Airport haltStation) {
        this.haltStation = haltStation;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
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


}
