package com.ericlara.flightBooker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;
@Component
public interface FlightBookRepository extends JpaRepository<FlightBook, Long> {
    List<FlightBook> findAllByUser(UserEntity user);
    void deleteFlightBookingById(Long id);
}
