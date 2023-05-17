package com.ericlara.flightBooker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;

@Component
public interface FlightBookRepository extends JpaRepository<FlightBook, Long> {
    /**
     * Returns a list of flight bookings for the given user.
     *
     * @param user The user to find flight bookings for.
     * @return A list of flight bookings for the given user.
     */
    List<FlightBook> findAllByUser(UserEntity user);

    /**
     * Deletes a flight booking by ID.
     *
     * @param id The ID of the flight booking to delete.
     */
    void deleteFlightBookingById(Long id);

}
