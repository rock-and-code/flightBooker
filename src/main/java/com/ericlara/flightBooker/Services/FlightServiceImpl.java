package com.ericlara.flightBooker.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightNotFoundException;
import com.ericlara.flightBooker.Repositories.FlightRepository;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
    
    @Autowired
    private FlightRepository flightRepository;

    // Returns all flights
    @Override
    public Iterable<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    // Finds flights by origin, destination, departure date, and number of available
    // seats
    @Override
    public Iterable<Flight> findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(String origin,
            String destination, LocalDate departureDate, int seats) {
        return flightRepository.findByOriginDestinationDepartureDateSeatsAvailable(origin, destination, departureDate,
                seats);
    }

    // Finds a flight by ID
    @Override
    public Flight findFlightById(Long id) throws FlightNotFoundException {
        // Check if flight exists in repository
        if (!flightRepository.findById(id).isPresent()) {
            throw new FlightNotFoundException();
        } else {
            return flightRepository.findById(id).get();
        }
    }

    // Finds flights by origin, departure date, and number of available seats
    @Override
    public Iterable<Flight> findFlightByOriginAndDepartureDateAndSeatsAvailable(String origin, LocalDate departureDate,
            int seats) {
        return flightRepository.findByOriginDepartureDateSeatsAvailable(origin, departureDate, seats);
    }

    // Finds flights by departure date
    @Override
    public List<Flight> findFlightsByDepartureDate(LocalDate date) {
        return flightRepository.findByDepartureDate(date);
    }

    // Saves a flight to the repository
    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Deletes a flight from the repository
    @Override
    public void deleteFlight(Flight flight) {
        flightRepository.delete(flight);
    }

    // Updates a flight by ID
    @Override
    public Flight updateFlightById(Long id, Flight flight) throws FlightNotFoundException {
        // Check if flight exists in repository
        if (!flightRepository.findById(id).isPresent()) {
            throw new FlightNotFoundException();
        } else {
            Flight existing = flightRepository.findById(id).get();
            // Update fields with new values
            existing.setOrigin(flight.getOrigin());
            existing.setDestination(flight.getDestination());
            existing.setAvailableSeats(flight.getAvailableSeats());
            existing.setFlightNumber(flight.getFlightNumber());
            existing.setDepartureDate(flight.getDepartureDate());
            existing.setDepartureTime(flight.getDepartureTime());
            Flight updated = flightRepository.save(existing);
            return updated;
        }
    }

    // Patches a flight by ID
    @Override
    public Flight patchFlightById(Long id, Flight flight) throws FlightNotFoundException {
        // Check if the flight with the given ID exists
        if (!flightRepository.findById(id).isPresent()) {
            // Throw a custom exception if the flight is not found
            throw new FlightNotFoundException();
        } else {
            // Retrieve the existing flight object from the repository
            Flight existing = flightRepository.findById(id).get();
            // Update the existing flight object with any non-null fields from the new
            // flight object
            if (flight.getOrigin() != null) {
                existing.setOrigin(flight.getOrigin());
            }
            if (flight.getDestination() != null) {
                existing.setDestination(flight.getDestination());
            }
            if (flight.getAvailableSeats() != null) {
                existing.setAvailableSeats(flight.getAvailableSeats());
            }
            if (flight.getFlightNumber() != null) {
                // Note: existing.getFlightNumber() should be replaced with
                // flight.getFlightNumber()
                // since existing.getFlightNumber() will always return a non-null value (line
                // 19)
                existing.setFlightNumber(flight.getFlightNumber());
            }
            if (flight.getDepartureDate() != null) {
                existing.setDepartureDate(flight.getDepartureDate());
            }
            if (flight.getDepartureTime() != null) {
                existing.setDepartureTime(flight.getDepartureTime());
            }
            // Save the updated flight object to the repository and return the patched
            // flight
            Flight patched = flightRepository.save(existing);
            return patched;
        }
    }

}
