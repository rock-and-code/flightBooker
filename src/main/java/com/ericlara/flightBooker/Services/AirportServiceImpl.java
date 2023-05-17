package com.ericlara.flightBooker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.Airport;
import com.ericlara.flightBooker.Repositories.AirportRepository;

@Service("airportService")
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    /**
     * Returns all airports.
     *
     * @return An iterable containing all airports.
     */
    @Override
    public Iterable<Airport> findAll() {
        // Calls the findAll method in the airportRepository to retrieve all airports
        return airportRepository.findAll();
    }

    /**
     * Returns all airports sorted by city in ascending order.
     *
     * @return An iterable containing all airports sorted by city in ascending order.
     */
    @Override
    public Iterable<Airport> findAllSortedASC() {
        // Calls the findAllSortedASC method in the airportRepository to retrieve all airports sorted by city in ascending order
        return airportRepository.findAllSortedASC();
    }
    
}

