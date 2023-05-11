package com.ericlara.flightBooker.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.Flight;
@Component
public interface FlightRepository extends CrudRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight WHERE origin = :origin AND destination = :destination AND departure_date = :departureDate AND available_seats >= :seats", nativeQuery = true)
    List<Flight> findByOriginDestinationDepartureDateSeatsAvailable(@Param("origin") String origin, @Param("destination") String destination, @Param("departureDate") LocalDate departureDate, @Param("seats") int seats);

    @Query(value = "SELECT * FROM flight WHERE origin = :origin AND departure_date = :departureDate AND available_seats >= :seats", nativeQuery = true)
    List<Flight> findByOriginDepartureDateSeatsAvailable(@Param("origin") String origin, @Param("departureDate") LocalDate departureDate, @Param("seats") int seats);

    @Query(value = "SELECT * FROM flight WHERE departure_date = :departureDate LIMIT 100", nativeQuery = true)
    List<Flight> findByDepartureDate(@Param("departureDate") LocalDate departureDate);
}
