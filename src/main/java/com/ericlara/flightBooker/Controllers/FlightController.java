package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ericlara.flightBooker.Models.*;
import com.ericlara.flightBooker.Services.*;

@Controller
@RequestMapping(value = "/")
public class FlightController {

    // Inject FlightService and AirportService instances
    private final FlightService flightService;
    private final AirportService airportService;

    public FlightController(@Qualifier("flightService") FlightService flightService,
            @Qualifier("airportService") AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    // Render flight search form
    @RequestMapping(method = RequestMethod.GET)
    public String flightsForm(@ModelAttribute FlightDTO flightDTO, Model model) {
        // To limit the date picker from today to its max date (12/31/2120) source:
        // https://stackoverflow.com/questions/47763292/thymeleaf-html5-datepicker-setting-min-date-from-variable-not-working
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        model.addAttribute("flightDTO", new FlightDTO());
        model.addAttribute("airports", airportService.findAllSortedASC());
        return "flightSearchForm";
    }

    // Retrieve all flights and display them
    @GetMapping(value = "flights")
    public String getAllFlights(@RequestParam(name = "origin") String origin, 
            @RequestParam(name = "destination") String destination, 
            @RequestParam(name = "departureDate") String departureDate, Model model) {
        // To limit the date picker from today to its max date (12/31/2120) source:
        LocalDate today = LocalDate.parse(departureDate);
        model.addAttribute("airports", airportService.findAll());
        model.addAttribute("flights", flightService.findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(origin, destination, today, 1));
        return "flights";
    }

    // Handle flight search form submission
    @PostMapping(value = "flights")
    public String flightsSubmit(@ModelAttribute FlightDTO flightDTO, Model model) {
        // Make a query using flightSearchQuery data
        String origin = flightDTO.getOrigin();
        String destination = flightDTO.getDestination();
        LocalDate departureDate = flightDTO.getDepartureDate();
        int passengers = flightDTO.getNumOfPassengers();

        // System.out.println("Origin: " + origin + " Destination: " + destination 
        //     + " Departure Date: " + departureDate + " No Passengers: " + passengers);

        model.addAttribute("flightSearchQuery", flightDTO);
        model.addAttribute("flights",
                flightService.findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(origin,
                        destination, departureDate, passengers));

        return "flights";
    }

    // Retrieve flight details by ID and display them
    @RequestMapping(value = "flights/{id}", method = RequestMethod.GET)
    public String getFlightDetails(@PathVariable(name = "id", required = false) Long id, Model model) {
        try {
            // Add the found flight to the model
            model.addAttribute("flight", flightService.findFlightById(id));
        } catch (FlightNotFoundException e) {
            // Return a message if the flight was not found
            return new FlightNotFoundException().getMessage();
        }
        return "flightDetails";
    }

}
