package com.ericlara.flightBooker.Services;

import java.util.Optional;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;

public interface FlightBookService {

    /**
     * Finds a flight book by ID.
     *
     * @param id The ID of the flight book to find.
     * @return An Optional containing the flight book, if it exists.
     */
    public Optional<FlightBook> findById(Long id);

    /**
     * Saves a flight book.
     *
     * @param flightBook The flight book to save.
     */
    public void save(FlightBook flightBook);

    /**
     * Finds all flight books for a given user.
     *
     * @param user The user to find flight books for.
     * @return An iterable containing all flight books for the user.
     */
    public Iterable<FlightBook> findAllByUser(UserEntity user);

    /**
     * Deletes a flight book.
     *
     * @param flightBook The flight book to delete.
     */
    public void delete(FlightBook flightBook);
}
