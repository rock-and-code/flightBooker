package com.ericlara.flightBooker.Models;

import java.time.LocalDate;

public class FlightDTO {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int numOfPassengers;
    
    
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public int getNumOfPassengers() {
        return numOfPassengers;
    }
    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public FlightDTO origin(String origin) {
        this.origin = origin;
        return this;
    }
    public FlightDTO destination(String destination) {
        this.destination = destination;
        return this;
    }
    public FlightDTO departureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }
    public FlightDTO numOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
        return this;
    }

    
    
}
