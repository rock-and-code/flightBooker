package com.ericlara.flightBooker.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;

public interface FlightBookRepository extends CrudRepository<FlightBook, Long> {
    List<FlightBook> findAllByUser(UserEntity user);
    void deleteFlightBookingById(Long id);
}
