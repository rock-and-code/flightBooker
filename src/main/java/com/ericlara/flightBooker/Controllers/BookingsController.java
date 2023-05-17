package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ericlara.flightBooker.Models.BookingDto;
import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.FlightNotFoundException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/bookings/flights/{id}")
public class BookingsController {

    // Inject FlightService and AirportService instances
    @Autowired
    private final FlightService flightService;
    @Autowired
    private final FlightBookService flightBookService;
    @Autowired
    private UserService userService;

    public BookingsController(@Qualifier("flightService") FlightService flightService,
            @Qualifier("flightBookService") FlightBookService flightBookService) {
        this.flightService = flightService;
        this.flightBookService = flightBookService;
    }

    // Render flight search form
    @GetMapping
    public String checkoutForm(@PathVariable(name = "id", required = false) Long id,
            @ModelAttribute BookingDto bookingDTO, HttpServletResponse response,
            HttpSession session, Model model) throws AuthenticationException {
        // Get the logged-in user's email and add it to the model
        UserEntity currentUser = getLoggedInUserInfo();
        if (currentUser == null) {
            throw new AuthenticationException();
        }
        model.addAttribute("userEmail", currentUser.getEmail());

        // Get the flight from the database and add it to the model
        try {
            Flight flight = flightService.findFlightById(id);
            model.addAttribute("flight", flight);
        } catch (FlightNotFoundException e) {
            // Redirect to the flights page with an error message if the flight was not
            // found
            return "redirect:/?flightNotFound";
        }

        // Create a new BookingDto object and add it to the model
        model.addAttribute("bookingDTO", new BookingDto());

        // Return the view name for the checkout form
        return "bookings/bookingCheckoutForm";
    }

    // Add flight to User after pressing book now button on flight details view
    @PostMapping
    public String bookFlight(@ModelAttribute BookingDto bookingDto,
            @PathVariable(name = "id", required = false) Long id, HttpServletResponse response,
            Model model) {
        // Get the current date and time
        LocalDate today = LocalDate.now();

        // Get the logged-in user's email and add it to the model
        UserEntity currentUser = getLoggedInUserInfo();

        // Get the number of passengers from the BookingDto object
        int passengers = bookingDto.getPassengers();

        try {
             // Try to find the flight from the database
            Flight flightToBook = flightService.findFlightById(id);

            // Create a new FlightBook object for each passenger
            for (int i = 0; i < passengers; ++i) {
                FlightBook flightBooking = new FlightBook(UUID.randomUUID(), today, currentUser, flightToBook);
                flightBookService.save(flightBooking);
            }

            // Redirect to the flights page with a success message
            return "redirect:/?successBooking";

        } catch (FlightNotFoundException e) {
            // If the flight was not found, redirect to the flights page with an error
            // message
            return "redirect:/?failedBooking";
        }

    }

    // METHOD TO GET AUTHENTICATED USER INFO
    // THIS HELPER METHOD IS USED TO ASSIGN BOOKINGS TO CORRESPONDING USERS AND
    // TO DETERMINE WHETHER TO DISPLAY LOG IN OR LOG OUT BUTTONS.
    private UserEntity getLoggedInUserInfo() {
        // Get the authentication object from the SecurityContextHolder
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authenticated, return null
        if (loggedInUser == null) {
            return null;
        }

        // Get the user's name from the authentication object
        String userName = loggedInUser.getName();

        // Get the user from the database
        return userService.findUserByEmail(userName);
    }
}
