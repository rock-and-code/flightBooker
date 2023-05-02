package com.ericlara.flightBooker.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ericlara.flightBooker.Models.Airport;

public interface AirportRepository extends CrudRepository<Airport, Long> {

    // Query to find all airports sorted by city in ascending order
    @Query(value ="SELECT * FROM AIRPORT ORDER BY city ASC", nativeQuery = true)
    public List<Airport> findAllSortedASC();
}
