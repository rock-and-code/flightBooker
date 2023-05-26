package com.ericlara.flightBooker.Services;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightNotFoundException;

public interface FlightService {

    // Returns all flights
    public Iterable<Flight> findAllFlights();

    // Returns flights with the given origin, destination, departure date, and minimum number of available seats
    public Iterable<Flight> findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(String origin, String destination, LocalDate departureDate, int seats);

    // Returns flights with the given origin, departure date, and minimum number of available seats
    public Iterable<Flight> findFlightByOriginAndDepartureDateAndSeatsAvailable(String origin, LocalDate departureDate, int seats);

    // Returns the flight with the given ID, or throws a FlightNotFoundException if it does not exist
    public Flight findFlightById(Long id) throws FlightNotFoundException;

    // Returns all flights with the given departure date
    public Page<Flight> findFlightsByDepartureDate(LocalDate date, Integer pageNumber, Integer pageSize);

    // Returns all flights starting from a given departure date
    public Page<Flight> findFlightsFromDepartureDate(LocalDate date, Integer pageNumber, Integer pageSize);

    // Saves the given flight and returns the saved flight
    public Flight saveFlight(Flight flight);

    // Deletes the given flight
    public void deleteFlight(Flight flight);

    public void deleteFlightById(Long id);

    // Updates the flight with the given ID using the data in the given flight object, or throws a FlightNotFoundException if the flight does not exist
    public Flight updateFlightById(Long id, Flight flight) throws FlightNotFoundException;

    // Updates only the non-null fields of the flight with the given ID using the data in the given flight object, or throws a FlightNotFoundException if the flight does not exist
    public Flight patchFlightById(Long id, Flight flight) throws FlightNotFoundException;
}
