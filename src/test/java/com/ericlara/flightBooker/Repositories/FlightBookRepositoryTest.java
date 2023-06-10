package com.ericlara.flightBooker.Repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ericlara.flightBooker.Models.Booking;
import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.UserEntity;
@DataJpaTest
public class FlightBookRepositoryTest {
    @Autowired
    private FlightBookRepository flightBookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;

    private UserEntity testUserEntity;
    private Flight testFlight;
    private Booking testFlightBook;
    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity("User", "UserLastName", "user@email.com", "password", null, null);
        testUserEntity = userRepository.save(userEntity);
        Flight flight = Flight.builder()
            .availableSeats(50)
            .origin("New York, NY (JFK)")
            .destination("San Juan, PR (SJA)")
            .departureDate(LocalDate.now())
            .departureTime(LocalTime.now())
            .flightNumber("TX1256")
            .build();
        testFlight = flightRepository.save(flight);
        Booking booking = new Booking(UUID.randomUUID(), LocalDate.now(), testUserEntity, testFlight);
        testFlightBook = flightBookRepository.save(booking);
    }
    @Test
    void testDeleteFlightBookingById() {
        Long flightBookRepositoryCount = flightBookRepository.count();
        flightBookRepository.delete(testFlightBook);
        Long expResult = 0L;
        Long result = flightBookRepository.count();
        System.out.println("before delete operation: " + flightBookRepositoryCount + " after delete operation: " + result);
        assertEquals(expResult, result);
    }

    @Test
    void testFindAllByUser() {
        List<Booking> bookings = flightBookRepository.findAllByUser(testUserEntity);
        int result = bookings.size();
        int expResult = 1;
        assertEquals(expResult, result);
    }
}
