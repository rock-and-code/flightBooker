// File: FlightRestController.java
// Package: com.ericlara.flightSearch.Controllers
// Description: REST controller for flight operations

package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ericlara.flightBooker.Models.*;
import com.ericlara.flightBooker.Services.*;

@RestController
@RequestMapping(value = "/api/v1/flights")
public class FlightRestController {

    private final FlightService flightService;

    // Constructor that injects dependencies for FlightService and AirportService
    public FlightRestController(@Qualifier("flightService") FlightService flightService,
                                 @Qualifier("airportService") AirportService airportService) {
        this.flightService = flightService;
    }

    // Endpoint to update a flight by ID
    @PutMapping(value = "{flightId}")
    public ResponseEntity<Flight> updateFlightById(@PathVariable("flightId")Long id, @RequestBody Flight flight) {

        try {
            // Try to update the flight
            return new ResponseEntity<>(flightService.updateFlightById(id, flight), HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            // If the flight is not found, return a response with HTTP status code 302 (Found)
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to partially update a flight by ID
    @PatchMapping(value = "{flightId}")
    public ResponseEntity<Flight> patchFlightById(@PathVariable("flightId")Long id, @RequestBody Flight flight) {
        try {
            // Try to partially update the flight
            return new ResponseEntity<>(flightService.patchFlightById(id, flight), HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            // If the flight is not found, return a response with HTTP status code 302 (Found)
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to save a flight
    @PostMapping
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {
        // Save the flight
        Flight savedFlight = flightService.saveFlight(flight);
        HttpHeaders headers = new HttpHeaders();
        // Set the Location header to the URL of the saved flight
        headers.add("Location", "/api/v1/flights/" + savedFlight.getId());
        // Return a response with HTTP status code 201 (Created)
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // Endpoint to get all flights for today
    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights() {
        LocalDate today = LocalDate.now();
        // Get all flights with departure date equals to today
        return flightService.findFlightsByDepartureDate(today);
    }

    // Endpoint to get a flight by ID
    @RequestMapping(value = "{flightId}", method = RequestMethod.GET)
    public ResponseEntity<Flight> getFlightById(@PathVariable("flightId") Long id) {
        try {
            // Try to find the flight by ID
            return new ResponseEntity<>(flightService.findFlightById(id), HttpStatus.OK);
        } catch (FlightNotFoundException e) {
            // If the flight is not found, return a response with HTTP status code 302 (Found)
            return ResponseEntity.notFound().build();

        }
    }

    // Endpoint to delete a flight by ID
    @DeleteMapping(value = "{flightId}")
    public ResponseEntity<Flight> deleteflightById(@PathVariable("flightId") Long id) {
        try {
            // Try to find and delete the flight by ID
            Flight flightDeleted = flightService.findFlightById(id);
            flightService.deleteFlight(flightDeleted);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FlightNotFoundException e) {
            // If the flight is not found, return a ResponseEntity with a CONFLICT status code and an error message
            return ResponseEntity.notFound().build();
        }
    }

   

}
