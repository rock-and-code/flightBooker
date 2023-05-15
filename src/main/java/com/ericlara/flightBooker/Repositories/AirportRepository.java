package com.ericlara.flightBooker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.Airport;
@Component
public interface AirportRepository extends JpaRepository<Airport, Long> {

    // Query to find all airports sorted by city in ascending order
    @Query(value ="SELECT * FROM AIRPORT ORDER BY city ASC", nativeQuery = true)
    public List<Airport> findAllSortedASC();
}
