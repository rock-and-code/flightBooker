// File: FlightRestController.java
// Package: com.ericlara.flightSearch.Controllers
// Description: REST controller for flight operations

package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightNotFoundException;
import com.ericlara.flightBooker.Services.FlightService;

@RestController
public class FlightRestController {

  // The base URL for all flight-related endpoints
  public static final String FLIGHT_PATH = "/api/v1/flights";

  // The URL for a specific flight endpoint
  public static final String FLIGHT_PATH_ID = FLIGHT_PATH + "/{flightId}";

  // The flight service that provides access to flight data
  @Autowired
  private FlightService flightService;


  // Endpoint to update a flight by ID
  @PutMapping(value = FLIGHT_PATH_ID, produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flight> updateFlightById(@PathVariable("flightId") Long id, 
                                                 @RequestBody Flight flight) {

    // Try to update the flight
    try {
      return new ResponseEntity<>(flightService.updateFlightById(id, flight), HttpStatus.NO_CONTENT);
    } catch (FlightNotFoundException e) {
      // If the flight is not found, return a response with HTTP status code 302 (Found)
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to partially update a flight by ID
  @PatchMapping(value = FLIGHT_PATH_ID, produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flight> patchFlightById(@PathVariable("flightId") Long id, 
                                                @RequestBody Flight flight) {

    // Try to partially update the flight
    try {
      return new ResponseEntity<>(flightService.patchFlightById(id, flight), HttpStatus.NO_CONTENT);
    } catch (FlightNotFoundException e) {
      // If the flight is not found, return a response with HTTP status code 302 (Found)
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to save a flight
  @PostMapping(value = FLIGHT_PATH, produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight) {

    // Save the flight
    Flight savedFlight = flightService.saveFlight(flight);

    // Set the Location header to the URL of the saved flight
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/flights/" + savedFlight.getId());

    // Return a response with HTTP status code 201 (Created)
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  // Endpoint to get all flights for today
  @GetMapping(value = FLIGHT_PATH + "/today", produces =  MediaType.APPLICATION_JSON_VALUE)
  public Page<Flight> getFlights(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize) {

    // Get the current date
    LocalDate today = LocalDate.now();

    if(pageNumber == null) {
      pageNumber = 0;
    }

    if(pageSize == null) {
      pageSize = 25;
    }

    // Get all flights with departure date equals to today
    return flightService.findFlightsByDepartureDate(today, pageNumber, pageSize);
  }

  // Endpoint to get all flights for today
  @GetMapping(value = FLIGHT_PATH, produces =  MediaType.APPLICATION_JSON_VALUE)
  public Page<Flight> getFlightsStartingByCurrentDate(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                 @RequestParam(name = "pageSize", required = false) Integer pageSize) {

    // Get the current date
    LocalDate today = LocalDate.now();

    if(pageNumber == null) {
      pageNumber = 0;
    }

    if(pageSize == null) {
      pageSize = 25;
    }

    // Get all flights with departure date equals to today
    return flightService.findFlightsFromDepartureDate(today, pageNumber, pageSize);
  }

  // Endpoint to get a flight by ID
  @GetMapping(value = FLIGHT_PATH_ID, produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flight> getFlightById(@PathVariable("flightId") Long id) {

    // Try to find the flight by ID
    try {
      return new ResponseEntity<>(flightService.findFlightById(id), HttpStatus.OK);
    } catch (FlightNotFoundException e) {
      // If the flight is not found, return a response with HTTP status code 302 (Found)
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to delete a flight by ID
  @DeleteMapping(value = FLIGHT_PATH_ID, produces =  MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Flight> deleteflightById(@PathVariable("flightId") Long id) {

    // Try to find and delete the flight by ID
    flightService.deleteFlightById(id);

    // Return a response with HTTP status code 204 (No Content)
    return ResponseEntity.noContent().build();
  }

}
