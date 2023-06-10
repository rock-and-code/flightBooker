package com.ericlara.flightBooker.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.Booking;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Repositories.FlightBookRepository;
@Service("flightBookService")
public class FlightBookServiceImpl implements FlightBookService {

    @Autowired
    private FlightBookRepository flightBookRepository;    

    /**
     * Finds a flight book by ID.
     *
     * @param id The ID of the flight book to find.
     * @return An Optional containing the flight book, if it exists.
     */
    @Override
    public Optional<Booking> findById(Long id) {
        // Finds the flight book with the given ID from the repository.
        return flightBookRepository.findById(id);
    }

    /**
     * Saves a flight book.
     *
     * @param flightBook The flight book to save.
     */
    @Override
    public void save(Booking flightBook) {
        // Saves the flight book to the repository.
        flightBookRepository.save(flightBook);
    }

    /**
     * Finds all flight books for a user.
     *
     * @param user The user to find flight books for.
     * @return An iterable containing all flight books for the user.
     */
    @Override
    public Iterable<Booking> findAllByUser(UserEntity user) {
        // Finds all flight books for the given user from the repository.
        return flightBookRepository.findAllByUser(user);
    }

    /**
     * Deletes a flight book.
     *
     * @param flightBook The flight book to delete.
     */
    @Override
    public void delete(Booking flightBook) {
        // Deletes the flight book from the repository.
        flightBookRepository.delete(flightBook);
    }

}

 