package com.ericlara.flightBooker.Services;

import com.ericlara.flightBooker.Models.Airport;

public interface AirportService {

    /**
     * Returns all airports.
     *
     * @return An iterable containing all airports.
     */
    public Iterable<Airport> findAll();

    /**
     * Returns all airports sorted by city in ascending order.
     *
     * @return An iterable containing all airports sorted by city in ascending order.
     */
    public Iterable<Airport> findAllSortedASC();
}
