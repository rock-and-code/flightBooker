package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ericlara.flightBooker.util.TbConstants;

public class UserEntityTest {

    private UserEntity instance;
    private PasswordEncoder passwordEncoder;
    private Role role;
    private Booking booking;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();

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
        // Creating booking to test getBookings functionality
        booking = Booking.builder()
                .id(1L)
                .bookingNumber(UUID.fromString("f3d9f084-21cb-45b2-91ca-20373b8bfe97"))
                .bookedDate(LocalDate.of(2022, 06, 05))
                .user(instance)
                .flight(flight)
                .build();

        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking);

        // Creating new user
        instance = new UserEntity("userFirstName",
                "userLastName",
                "user@email.com",
                passwordEncoder.encode("password"),
                new ArrayList<>(),
                bookings);

        instance.setId(1L);

        role = new Role(TbConstants.Roles.USER);
        role.setId(1L);
        role.setUsers(List.of(instance));

        instance.setRoles(Arrays.asList(role));
        

    }

    @Test
    void testGetEmail() {
        String expResult = "user@email.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFirstName() {
        String expResult = "userFirstName";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFlightbookings() {
        Set<Booking> expResult = new HashSet<>();
        expResult.add(booking);
        Set<Booking> result = instance.getFlightbookings();
        assertEquals(expResult, result);
    }

    @Test
    void testGetId() {
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void testGetLastName() {
        String expResult = "userLastName";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    @Test
    void testGetPassword() {
        String result = instance.getPassword();
        System.out.println("Pass: " + result);
        assertTrue(result.length() == 60);
    }

    @Test
    void testGetRoles() {
        List<Role> expResult = Arrays.asList(role);
        List<Role> result = instance.getRoles();
        assertEquals(expResult, result);
    }
}
