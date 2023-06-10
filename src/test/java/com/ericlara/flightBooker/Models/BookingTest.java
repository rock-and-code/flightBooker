package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ericlara.flightBooker.util.TbConstants;

public class BookingTest {

    private Booking instance;
    private PasswordEncoder passwordEncoder;
    private Flight flight;
    private UserEntity user;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        //Creating a flight for booking
        flight = Flight.builder()
        .id(1L)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(AirportData.usAirports.get(0).getFormattedLocation())
        .destination(AirportData.usAirports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
        //Creating a role for the booking user
        Role role = new Role(TbConstants.Roles.USER);
        //Creating a user for booking
        user = new UserEntity("userFirstName", 
            "userLastName", 
            "user@email.com",
            passwordEncoder.encode("password"),
            Arrays.asList(role), 
            new HashSet<>());
        instance = Booking.builder()
        .id(1L)
        .bookingNumber(UUID.fromString("f3d9f084-21cb-45b2-91ca-20373b8bfe97"))
        .bookedDate(LocalDate.of(2022, 06, 05))
        .user(user)
        .flight(flight)
        .build();
    }

    @Test
    void testGetBookedDate() {
        LocalDate expResult = LocalDate.of(2022, 06, 05);
        LocalDate result = instance.getBookedDate();
        assertEquals(expResult, result);
    }

    @Test
    void testGetBookingNumber() {
        UUID expResult = UUID.fromString("f3d9f084-21cb-45b2-91ca-20373b8bfe97");
        UUID result = instance.getBookingNumber();
        System.out.println(String.format("ExpResult=%s : Result=%s", expResult, result.toString()));
        assertEquals(expResult, result);
    }

    @Test
    void testGetFlight() {
        Flight expResult = flight;
        Flight result = instance.getFlight();
        assertEquals(expResult, result);
    }

    @Test
    void testGetId() {
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
}
