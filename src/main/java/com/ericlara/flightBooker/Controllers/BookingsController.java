package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.FlightNotFoundException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/bookings/flights/{id}")
public class BookingsController {

    // Inject FlightService and AirportService instances
    private final FlightService flightService;
    private final FlightBookService flightBookService;
    @Autowired
    private UserService userService;

    public BookingsController(@Qualifier("flightService") FlightService flightService, 
     @Qualifier("flightBookService") FlightBookService flightBookService) {
        this.flightService = flightService;
        this.flightBookService = flightBookService;
    }

    // Add flight to User after pressing book now button on flight details view
    @PostMapping
    public String bookFlight(@PathVariable(name = "id", required = false) Long id, HttpServletResponse response,
        Model model) {
        LocalDate today = LocalDate.now(); //TO RECORD THE TRANSACTION DATE
        UserEntity currentUser = getLoggedInUserInfo();
        try {
            Flight flightToBook = flightService.findFlightById(id);
            //BOOK FLIGHT
            FlightBook flightBooking = new FlightBook(UUID.randomUUID(), today, currentUser, flightToBook);
            flightBookService.save(flightBooking);
        } catch (FlightNotFoundException e) {
            return "redirect:/?failedBooking"; //FEEDBACK ALERT ON FAILED FLIGHT BOOKING
        }
        return "redirect:/?successBooking"; //FEEDBACK ALERT ON FLIGHT_SEARCH_FORM
    }


    private UserEntity getLoggedInUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return userService.findUserByEmail(userName);
    }
}
