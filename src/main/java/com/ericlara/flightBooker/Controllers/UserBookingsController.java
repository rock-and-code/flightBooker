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

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletResponse;

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

    //METHOD TO DISPLAY USER'S BOOKINGS
    @PostMapping
    public String getUserBookings(HttpServletResponse response, Model model) {
        UserEntity currentUser = getLoggedInUserInfo();

        model.addAttribute("userEmail", currentUser.getEmail());
        model.addAttribute("bookings", flightBookService.findAllByUser(currentUser));
        return "bookings/userBookings";
    }

    //METHOD TO CANCEL ONE USER'S BOOKING AT A TIME
    @PostMapping(value = "{flightBookingId}")
    public String deleteUserBooking(@PathVariable("flightBookingId")Long id, HttpServletResponse response, Model model) {
        Optional<FlightBook> flightBookToDelete = flightBookService.findById(id);
        //DELETE USER'S FLIGHT BOOKING
        //TODO: PROVIDE A CONFIRMATION MECHANISM TO AVOID CANCELLING BOOKING BY ERROR
        flightBookService.delete(flightBookToDelete.get());
        UserEntity currentUser = getLoggedInUserInfo();
        model.addAttribute("userEmail", currentUser.getEmail());
        model.addAttribute("bookings", flightBookService.findAllByUser(currentUser));
        return "bookings/userBookings";
    }


    //METHOD TO GET AUTHENTICATED USER INFO
    //THIS HELPER METHOD IS USED TO ASSIGN BOOKINGS TO CORRESPONDING USERS AND 
    //TO DETERMINE WHETHER TO DISPLAY LOG IN OR LOG OUT BUTTONS,
    //AND TO DISPLAY THE USERS'S BOOKINGS 
    private UserEntity getLoggedInUserInfo() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName(); //Username = email
        return userService.findUserByEmail(userName);
    }
}
