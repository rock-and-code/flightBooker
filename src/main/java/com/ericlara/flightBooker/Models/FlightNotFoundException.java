package com.ericlara.flightBooker.Models;

public class FlightNotFoundException extends Exception {
    public FlightNotFoundException(String message) {
        super(message);
    }

    public FlightNotFoundException() {
        super("Flight Not Found");
    }
}