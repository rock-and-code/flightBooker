package com.ericlara.flightBooker.Controllers;

import java.time.LocalDate;
import java.util.UUID;

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
            HttpSession session, Model model) {
        UserEntity currentUser = getLoggedInUserInfo();
        model.addAttribute("userEmail", currentUser.getEmail());
        try {
            Flight flight = flightService.findFlightById(id);
            model.addAttribute("flight", flight);
        } catch (FlightNotFoundException e) {
            // TODO: REDIRECTS TO FLIGHT SEARCH FORM AND ADD FEEDBACK MESSAGE
            return "redirect:/?flightNotFound";
        }

        model.addAttribute("bookingDTO", new BookingDto());
        return "bookingCheckoutForm";
    }

    // Add flight to User after pressing book now button on flight details view
    @PostMapping
    public String bookFlight(@ModelAttribute BookingDto bookingDto, 
        @PathVariable(name = "id", required = false) Long id, HttpServletResponse response,
        Model model) {
        LocalDate today = LocalDate.now(); // TO RECORD THE TRANSACTION DATE
        UserEntity currentUser = getLoggedInUserInfo();
        int passengers = bookingDto.getPassengers();
        try {
            Flight flightToBook = flightService.findFlightById(id);
            // BOOK FLIGHT
            for (int i=0; i<passengers; ++i) {
                FlightBook flightBooking = new FlightBook(UUID.randomUUID(), today, currentUser, flightToBook);
                flightBookService.save(flightBooking);
            } 
        } catch (FlightNotFoundException e) {
            return "redirect:/?failedBooking"; // FEEDBACK ALERT ON FAILED FLIGHT BOOKING
        }
        return "redirect:/?successBooking"; // FEEDBACK ALERT ON FLIGHT_SEARCH_FORM
    }

    private UserEntity getLoggedInUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        return userService.findUserByEmail(userName);
    }
}
