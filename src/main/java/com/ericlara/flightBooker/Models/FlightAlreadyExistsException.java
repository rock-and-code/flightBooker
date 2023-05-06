package com.ericlara.flightBooker.Models;

public class FlightAlreadyExistsException extends Exception {
    public FlightAlreadyExistsException(String message) {
        super(message);
    }
    public FlightAlreadyExistsException() {
        super("Flight Already Exists");
    }
}
