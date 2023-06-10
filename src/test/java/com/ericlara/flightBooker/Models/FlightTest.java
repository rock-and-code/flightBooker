package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightTest {

    private List<Airport> airports;

    private Flight instance;

    @BeforeEach
    void setUp() {
        airports = List.of(AirportData.usAirports.get(0), AirportData.usAirports.get(1));
        instance = Flight.builder()
        .id(1L)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(airports.get(0).getFormattedLocation())
        .destination(airports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
    }

    @Test
    void testDecrementAvailableSeats() {
        int expResult = 49;
        instance.decrementAvailableSeats();
        int result = instance.getAvailableSeats();
        assertEquals(expResult, result);
    }

    @Test
    void testEquals() {
        Flight anotherInstance = Flight.builder()
        .id(1L)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(airports.get(0).getFormattedLocation())
        .destination(airports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
        
        boolean expResult = true;
        boolean result = instance.equals(anotherInstance);
        assertEquals(expResult, result);
    }

    @Test
    void testGetAvailableSeats() {
        int expResult = 50;
        int result = instance.getAvailableSeats();
        assertEquals(expResult, result);
    }

    @Test
    void testGetBookings() {
        Set<Booking> expResult = new HashSet<>();
        Set<Booking> result = instance.getBookings();
        assertEquals(expResult, result);
    }

    @Test
    void testGetDepartureDate() {
        LocalDate expResult = LocalDate.of(2022, 05, 30);
        LocalDate result = instance.getDepartureDate();
        assertEquals(expResult, result);
    }

    @Test
    void testGetDepartureTime() {
        LocalTime expResult = LocalTime.of(12, 30, 0);
        LocalTime result = instance.getDepartureTime();
        assertEquals(expResult, result);
    }

    @Test
    void testGetDestination() {
        Airport airport = airports.get(1);
        String expResult = airport.getCity() + ", " + airport.getState() + " (" + airport.getIataId() + ")";
        String result = instance.getDestination();
        assertEquals(expResult, result);
    }

    @Test
    void testGetDestinationAddress() {
        String expResult = instance.getDestination().substring(0, instance.getDestination().length() - 6);
        String result = instance.getDestinationAddress();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFlightNumber() {
        String expResult = "MX6789";
        String result = instance.getFlightNumber();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFormattedDepartureTime() {
        String pattern = "hh:mm a";
        String expResult = LocalTime.of(12, 30, 0).format(DateTimeFormatter.ofPattern(pattern));
        String result = instance.getFormattedDepartureTime();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFormattedPrice() {
        String expResult = String.format("$%,.2f", 150.45);;
        String result = instance.getFormattedPrice();
        assertEquals(expResult, result);
    }

    @Test
    void testGetId() {
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void testGetOrigin() {
        Airport airport = airports.get(0);
        String expResult = airport.getCity() + ", " + airport.getState() + " (" + airport.getIataId() + ")";
        String result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    @Test
    void testGetPrice() {
        double expResult = 150.45;
        double result = instance.getPrice();
        assertEquals(expResult, result);
    }

    @Test
    void testIncrementAvailableSeats() {
        int expResult = 51;
        instance.incrementAvailableSeats();
        int result = instance.getAvailableSeats();
        assertEquals(expResult, result);
    }
}
