package com.ericlara.flightBooker.Models;

public class FlightNotFoundException extends Exception {
    private String message;
    public FlightNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public FlightNotFoundException() {
        super("Flight Not Found");
        this.message = "Flight Not Found";
    }
}