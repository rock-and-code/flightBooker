package com.ericlara.flightBooker.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.Flight;
@Component
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * Returns a list of flights that match the given origin, destination, departure date, and number of seats available.
     *
     * @param origin The origin of the flight.
     * @param destination The destination of the flight.
     * @param departureDate The departure date of the flight.
     * @param seats The number of seats available on the flight.
     * @return A list of flights that match the given criteria.
     */
    @Query(value = "SELECT * FROM flight WHERE origin = :origin AND destination = :destination AND departure_date = :departureDate AND available_seats >= :seats", nativeQuery = true)
    List<Flight> findByOriginDestinationDepartureDateSeatsAvailable(@Param("origin") String origin, @Param("destination") String destination, @Param("departureDate") LocalDate departureDate, @Param("seats") int seats);

    /**
     * Returns a list of flights that match the given origin, departure date, and number of seats available.
     *
     * @param origin The origin of the flight.
     * @param departureDate The departure date of the flight.
     * @param seats The number of seats available on the flight.
     * @return A list of flights that match the given criteria.
     */
    @Query(value = "SELECT * FROM flight WHERE origin = :origin AND departure_date = :departureDate AND available_seats >= :seats", nativeQuery = true)
    List<Flight> findByOriginDepartureDateSeatsAvailable(@Param("origin") String origin, @Param("departureDate") LocalDate departureDate, @Param("seats") int seats);

    /**
     * Returns a list of flights that match the given departure date.
     *
     * @param departureDate The departure date of the flight.
     * @return A list of flights that match the given criteria.
     */
    @Query(value = "SELECT * FROM flight WHERE departure_date = :departureDate", nativeQuery = true)
    List<Flight> findByDepartureDate(@Param("departureDate") LocalDate departureDate, Pageable pageable);
}

