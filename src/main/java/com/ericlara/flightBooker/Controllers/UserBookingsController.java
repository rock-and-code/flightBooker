package com.ericlara.flightBooker.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericlara.flightBooker.Models.Booking;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/bookings/user")
public class UserBookingsController {

    // Inject FlightService and AirportService instances
    @Autowired
    private final FlightBookService flightBookService;
    @Autowired
    private UserService userService;

    public UserBookingsController(@Qualifier("flightBookService") FlightBookService flightBookService) {
        this.flightBookService = flightBookService;
    }

    // METHOD TO DISPLAY USER'S BOOKINGS
    @PostMapping
    public String getUserBookings(HttpServletRequest request, Model model) {
        // Get the logged-in user's email and add it to the model
        UserEntity currentUser = getLoggedInUserInfo();

        // Check if there are any unread bookings details to display badge notification
        HttpSession session = request.getSession();
        Integer unread_bookings_notification = (Integer) session.getAttribute("unread_bookings_details");

        // Clearing unread bookings badge notification since the user is requesting to
        // view the bookings details
        if (unread_bookings_notification != null) {
            session.removeAttribute("unread_bookings_details");
        }

        model.addAttribute("userEmail", currentUser.getEmail());

        // Get the user's bookings and add them to the model
        model.addAttribute("bookings", flightBookService.findAllByUser(currentUser));

        model.addAttribute("title", "Bookings");

        // Return the view name for the user's bookings
        return "bookings/userBookings";
    }

    // METHOD TO CANCEL ONE USER'S BOOKING AT A TIME
    @PostMapping(value = "{flightBookingId}")
    public String deleteUserBooking(@PathVariable("flightBookingId") Long id, HttpServletResponse response,
            Model model) {
        // Get the flight booking to delete
        Optional<Booking> flightBookToDelete = flightBookService.findById(id);

        // Delete the flight booking
        if (flightBookToDelete.isPresent()) {
            // Increment the flight's available seats since we are cancelling a booking
            flightBookToDelete.get().getFlight().incrementAvailableSeats();
            flightBookService.delete(flightBookToDelete.get());
        }

        // Get the logged-in user's email and add it to the model
        UserEntity currentUser = getLoggedInUserInfo();

        model.addAttribute("userEmail", currentUser.getEmail());

        // Get the user's bookings and add them to the model
        model.addAttribute("bookings", flightBookService.findAllByUser(currentUser));

        // Return the view name for the user's bookings
        return "bookings/userBookings";
    }

    // METHOD TO GET AUTHENTICATED USER INFO
    // THIS HELPER METHOD IS USED TO ASSIGN BOOKINGS TO CORRESPONDING USERS AND
    // TO DETERMINE WHETHER TO DISPLAY LOG IN OR LOG OUT BUTTONS,
    // AND TO DISPLAY THE USERS'S BOOKINGS
    private UserEntity getLoggedInUserInfo() {
        // Get the authentication object from the SecurityContextHolder
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        // If the user is not authenticated, return null
        if (loggedInUser == null) {
            return null;
        }

        // Get the user's name from the authentication object
        String userName = loggedInUser.getName(); // Username = email

        // Get the user from the database
        return userService.findUserByEmail(userName);
    }
}
