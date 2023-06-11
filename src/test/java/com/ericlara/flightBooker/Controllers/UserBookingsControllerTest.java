package com.ericlara.flightBooker.Controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ericlara.flightBooker.Models.AirportData;
import com.ericlara.flightBooker.Models.Booking;
import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.UserService;
import com.ericlara.flightBooker.util.TbConstants;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

@WebMvcTest(UserBookingsController.class)

@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY

@ExtendWith(MockitoExtension.class)
// This annotation tells JUnit to use the Mockito framework for mocking
// dependencies.
public class UserBookingsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    // This field is injected by Spring Boot. It provides a MockMvc instance that
    // can be used to test the FlightController class.

    @MockBean
    private AuthenticationManager authenticationManager;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // AuthenticationManager interface. This is necessary because we are testing the
    // FlightController class without Spring Security enabled.

    @MockBean(name = "userService")
    private UserService userService;

    @MockBean(name = "flightBookService")
    private FlightBookService flightBookService;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // UserService interface. This is necessary because we want to test the
    // FlightController class in isolation.

    private PasswordEncoder passwordEncoder;
    private UserEntity user;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() throws UserAlreadyExistsException {
        passwordEncoder = new BCryptPasswordEncoder();
        // Creating a role for the booking user
        Role role = new Role(TbConstants.Roles.USER);
        // Creating a user for booking
        user = new UserEntity("userFirstName",
                "userLastName",
                "user@email.com",
                passwordEncoder.encode("password"),
                Arrays.asList(role),
                new HashSet<>());

        Flight flight = Flight.builder()
        .id(1L)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(AirportData.usAirports.get(0).getFormattedLocation())
        .destination(AirportData.usAirports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
        
        Booking booking = Booking.builder()
        .id(1L)
        .bookingNumber(UUID.fromString("f3d9f084-21cb-45b2-91ca-20373b8bfe97"))
        .bookedDate(LocalDate.of(2022, 06, 05))
        .user(user)
        .flight(flight)
        .build();

        bookings = List.of(booking);

    }

    @Test
    @WithMockUser(username = "user@email.com", authorities = { "ROLE_USER" })
    void testDeleteUserBooking() throws Exception {
        Optional<Booking> booking = Optional.of(bookings.get(0));

        String userName = "user@email.com";
        Long bookingId = 1L;
        // To address custom authentication method in Booking controller
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(user);
        Mockito.when(userService.findUserByEmail(userName)).thenReturn(user);

        BDDMockito.given(flightBookService.findById(any(Long.class))).willReturn(booking);
        Mockito.when(flightBookService.findById(bookingId)).thenReturn(booking);

        this.mockMvc.perform(post("/bookings/user", 1L)
                .with(csrf())).andExpect(view().name("bookings/userBookings"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @WithMockUser(username = "user@email.com", authorities = { "ROLE_USER" })
    void testGetUserBookings() throws Exception {
        String userName = "user@email.com";
        // To address custom authentication method in Booking controller
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(user);
        Mockito.when(userService.findUserByEmail(userName)).thenReturn(user);

        BDDMockito.given(flightBookService.findAllByUser(any(UserEntity.class))).willReturn(bookings);
        Mockito.when(flightBookService.findAllByUser(user)).thenReturn(bookings);

        this.mockMvc.perform(post("/bookings/user")
                .with(csrf())).andExpect(view().name("bookings/userBookings"))
                .andDo(MockMvcResultHandlers.print());

    }
}
