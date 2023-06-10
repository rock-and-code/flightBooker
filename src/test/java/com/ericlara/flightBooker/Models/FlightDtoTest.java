package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightDtoTest {

    private List<Airport> airports;
    private FlightDto instance;

    @BeforeEach
    void setUp(){
        airports = List.of(AirportData.usAirports.get(0), AirportData.usAirports.get(1));
        instance = FlightDto.builder()
        .departureDate(LocalDate.of(2022, 05, 30))
        .destination(airports.get(1).getFormattedLocation())
        .origin(airports.get(0).getFormattedLocation())
        .numOfPassengers(3)
        .build();
    }

    @Test
    void testEquals() {
        FlightDto anotherInstance = FlightDto.builder()
        .departureDate(LocalDate.of(2022, 05, 30))
        .destination(airports.get(1).getFormattedLocation())
        .origin(airports.get(0).getFormattedLocation())
        .numOfPassengers(3)
        .build();
        boolean expResult = true;
        boolean result = instance.equals(anotherInstance);
        assertEquals(expResult, result);
    }

    @Test
    void testGetDepartureDate() {
        LocalDate expResult = LocalDate.of(2022, 05, 30);
        LocalDate result = instance.getDepartureDate();
        assertEquals(expResult, result);    
    }

    @Test
    void testGetDestination() {
        String expResult = airports.get(1).getFormattedLocation();
        String result = instance.getDestination();
        assertEquals(expResult, result); 
    }

    @Test
    void testGetNumOfPassengers() {
        int expResult = 3;
        int result = instance.getNumOfPassengers();
        assertEquals(expResult, result);
    }

    @Test
    void testGetOrigin() {
        String expResult = airports.get(0).getFormattedLocation();
        String result = instance.getOrigin();
        assertEquals(expResult, result); 
    }
}
