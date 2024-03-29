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
    public String flightsForm(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute FlightDto flightDto, Model model) {
        // To limit the date picker from today to its max date (12/31/2120) source:
        // https://stackoverflow.com/questions/47763292/thymeleaf-html5-datepicker-setting-min-date-from-variable-not-working
        // INBJECT A COOKIE FOR DEMOSTRATIONS PURPOSES
        // Cookie cookie = new Cookie("dummyCookie", "dummy_cookie");
        // cookie.setMaxAge(60*60*24*7); //To be saved for a week
        // cookie.setPath("/");
        // response.addCookie(cookie);

        // Check if flightDetails url has been saved to redirect user that have
        // registered
        // to book a flight
        HttpSession session = request.getSession();
        String redirectUrl = (String) session.getAttribute("url_prior_login");
        if (redirectUrl != null) {
            // Don't forget to clean this attribute from session
            session.removeAttribute("url_prior_login");
            return "redirect:" + redirectUrl;
        }

        // Check if there are any unread bookings details to display badge notification
        Integer unread_bookings_notification = (Integer) session.getAttribute("unread_bookings_details");

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

        // Get all unread bookings details and add them to the model
        model.addAttribute("unreadBookings", unread_bookings_notification);

        model.addAttribute("title", "Search Flights");

        // Return the "flights/flightSearchForm" view
        return "flights/flightSearchForm";
    }

    // Retrieve all flights and display them
    @GetMapping(value = "flights")
    public String getAllFlights(@RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination,
            @RequestParam(name = "departureDate") String departureDate,
            HttpServletRequest request, Model model) {

        if (origin.isBlank() || destination.isBlank() || departureDate.isBlank()) {
            return "redirect:/";
        }

        // Check if there are any unread bookings details to display badge notification
        HttpSession session = request.getSession();
        Integer unread_bookings_notification = (Integer) session.getAttribute("unread_bookings_details");

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

        // Get all unread bookings details and add them to the model
        model.addAttribute("unreadBookings", unread_bookings_notification);

        model.addAttribute("title", "Flights");

        // Return the "flights/flights" view
        return "flights/flights";
    }

    // Handle flight search form submission
    @PostMapping(value = "flights")
    public String searchResults(@ModelAttribute FlightDto flightDto, Model model, HttpServletRequest request) {

         // Check if there are any unread bookings details to display badge notification
         HttpSession session = request.getSession();
         Integer unread_bookings_notification = (Integer) session.getAttribute("unread_bookings_details");

        // Make a query using flightSearchQuery data
        String origin = flightDto.getOrigin();
        String destination = flightDto.getDestination();
        LocalDate departureDate = flightDto.getDepartureDate();
        int passengers = flightDto.getNumOfPassengers();

        // System.out.println("Origin: " + origin + " Destination: " + destination
        // + " Departure Date: " + departureDate + " No Passengers: " + passengers);
        model.addAttribute("userEmail", getLoggInUserEmail());
        model.addAttribute("flightSearchQuery", flightDto);
        model.addAttribute("flights",
                flightService.findFlightByOriginDestinationAndDepartureDateAndSeatsAvailable(origin,
                        destination, departureDate, passengers));
         // Get all unread bookings details and add them to the model
         model.addAttribute("unreadBookings", unread_bookings_notification);

        return "flights/flights";
    }

    // Retrieve flight details by ID and display them
    @GetMapping(value = "flights/{id}")
    public String getFlightDetails(@PathVariable(name = "id", required = false) Long id,
            HttpServletRequest request, Model model) {
        try {
            // Check if there are any unread bookings details to display badge notification
            HttpSession session = request.getSession();
            Integer unread_bookings_notification = (Integer) session.getAttribute("unread_bookings_details");

            // Add the found flight to the model
            model.addAttribute("flight", flightService.findFlightById(id));
            model.addAttribute("userEmail", getLoggInUserEmail());
            model.addAttribute("title", "Flight Details");
            // Get all unread bookings details and add them to the model
            model.addAttribute("unreadBookings", unread_bookings_notification);
            // Saving the fligh url in case user has to be redirected after login if the
            // user is
            // not authenticated
            if (getLoggInUserEmail() == null) {
                // String referrer = request.getHeader("Referer");
                String referrer = "/flights/" + id;
                request.getSession().setAttribute("url_prior_login", referrer);
            }
        } catch (FlightNotFoundException e) {
            // Redirects user to search form and displays a message if the flight was not
            // found
            return "redirect:/?flightNotFound";
        }

        return "flights/flightDetails";
    }

    // METHOD TO GET AUTHENTICATED USER EMAIL
    private String getLoggInUserEmail() {
        if (getLoggedInUserInfo() == null) { // User has not been authenticated yet
            return null;
        } else { // User is authenticated
            return getLoggedInUserInfo().getEmail();
        }
    }

    // METHOD TO GET AUTHENTICATED USER INFO
    // THIS HELPER METHOD IS USED TO ASSIGN BOOKINGS TO CORRESPONDING USERS AND
    // TO DETERMINE WHETHER TO DISPLAY LOG IN OR LOG OUT BUTTONS,
    // AND TO DISPLAY THE USERS'S BOOKINGS
    private UserEntity getLoggedInUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        if (loggedInUser == null) {
            return null;
        }
        String userName = loggedInUser.getName();
        // System.out.println("USER: " + userName);
        return userService.findUserByEmail(userName);
    }
}