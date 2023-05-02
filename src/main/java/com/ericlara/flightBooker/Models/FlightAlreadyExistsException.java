package com.ericlara.flightBooker.Models;

public class FlightAlreadyExistsException extends Exception {
    private String message;
    public FlightAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public FlightAlreadyExistsException() {
        super("Flight Already Exists");
        this.message = "Flight Already Exists";
    }
}
