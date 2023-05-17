package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ericlara.flightBooker.Models.FlightDto;
import com.ericlara.flightBooker.Models.FlightNotFoundException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.AirportService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class FlightController {

    // Inject FlightService and AirportService instances
    private final FlightService flightService;
    private final AirportService airportService;
    private final UserService userService;

    // Constructor
    public FlightController(@Qualifier("flightService") FlightService flightService,
            @Qualifier("airportService") AirportService airportService,
            @Qualifier("userService") UserService userService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.userService = userService;
    }

    // Render flight search form
    @GetMapping
    public String flightsForm(HttpServletResponse response,
            @ModelAttribute FlightDto flightDTO, HttpSession session, Model model) {
        // To limit the date picker from today to its max date (12/31/2120) source:
        // https://stackoverflow.com/questions/47763292/thymeleaf-html5-datepicker-setting-min-date-from-variable-not-working
        // INBJECT A COOKIE FOR DEMOSTRATIONS PURPOSES
        // Cookie cookie = new Cookie("dummyCookie", "dummy_cookie");
        // cookie.setMaxAge(60*60*24*7); //To be saved for a week
        // cookie.setPath("/");
        // response.addCookie(cookie);

        // Get the current date
        LocalDate today = LocalDate.now();

        // Add the current date to the model
        model.addAttribute("today", today);

        // Create a new FlightDto object and add it to the model
        model.addAttribute("flightDTO", new FlightDto());

        // Get the logged-in user's email and add it to the model
        model.addAttribute("userEmail", getLoggInUserEmail());

        // Get all airports and add them to the model
        model.addAttribute("airports", airportService.findAllSortedASC());

        // Return the "flights/flightSearchForm" view
        return "flights/flightSearchForm";
    }

    // Retrieve all flights and display them
    @GetMapping(value = "flights")
    public String getAllFlights(@RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination,
            @RequestParam(name = "departureDate") String departureDate, 
            HttpSession session, Model model) {

        // To limit the date picker from today to its max date (12/31/2120) source:
        // https://stackoverflow.com/questions/47763292/thymeleaf-html5-datepicker-setting-min-date-from-variable-not-working
        LocalDate today = LocalDate.parse(departureDate);

        // Get all airports and add them to the model
        model.addAttribute("airports", airportService.findAll());

        // Get the logged-in user's email and add it to the model
        model.addAttribute("userEmail", getLoggInUserEmail());

        // Get all flights that match the search criteria and add them to the model
        model.addAttribute("flights", flightService
                .findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(origin, destination, today, 1));

        // Return the "flights/flights" view
        return "flights/flights";
    }


    // Handle flight search form submission
    @PostMapping(value = "flights")
    public String flightsSubmit(@ModelAttribute FlightDto flightDTO, Model model) {
        // Make a query using flightSearchQuery data
        String origin = flightDTO.getOrigin();
        String destination = flightDTO.getDestination();
        LocalDate departureDate = flightDTO.getDepartureDate();
        int passengers = flightDTO.getNumOfPassengers();

        // System.out.println("Origin: " + origin + " Destination: " + destination
        // + " Departure Date: " + departureDate + " No Passengers: " + passengers);
        model.addAttribute("userEmail", getLoggInUserEmail());
        model.addAttribute("flightSearchQuery", flightDTO);
        model.addAttribute("flights",
                flightService.findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(origin,
                        destination, departureDate, passengers));

        return "flights/flights";
    }

    // Retrieve flight details by ID and display them
    @GetMapping(value = "flights/{id}")
    public String getFlightDetails(@PathVariable(name = "id", required = false) Long id,
        HttpServletRequest request, HttpSession session, Model model) {
        try {
            // Add the found flight to the model
            model.addAttribute("flight", flightService.findFlightById(id));
            model.addAttribute("userEmail", getLoggInUserEmail());
            //Saving the fligh url in case user has to be redirected after login
            //String referrer = request.getHeader("Referer");
            String referrer = "/flights/" + id;
            request.getSession().setAttribute("url_prior_login", referrer);
        } catch (FlightNotFoundException e) {
            // Redirects user to search form and displays a message if the flight was not found
            return "redirect:/?flightNotFound";
        }

        return "flights/flightDetails";
    }

     //METHOD TO GET AUTHENTICATED USER EMAIL
    private String getLoggInUserEmail() {
        if (getLoggedInUserInfo() == null) { //User has not been authenticated yet
            return null;
        } else { //User is authenticated
            return getLoggedInUserInfo().getEmail();
        }
    }

    //METHOD TO GET AUTHENTICATED USER INFO
    //THIS HELPER METHOD IS USED TO ASSIGN BOOKINGS TO CORRESPONDING USERS AND 
    //TO DETERMINE WHETHER TO DISPLAY LOG IN OR LOG OUT BUTTONS,
    //AND TO DISPLAY THE USERS'S BOOKINGS 
    private UserEntity getLoggedInUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        if(loggedInUser == null) {
            return null;
        }
        String userName = loggedInUser.getName();
        //System.out.println("USER: " + userName);
        return userService.findUserByEmail(userName);
    }
}
