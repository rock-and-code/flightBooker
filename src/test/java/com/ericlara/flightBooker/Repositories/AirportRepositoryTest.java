package com.ericlara.flightBooker.Repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ericlara.flightBooker.Models.Airport;
@DataJpaTest
public class AirportRepositoryTest {
    @Autowired
    private AirportRepository airportRepository;
    @Test
    void testFindAllSortedASC() {
        List<Airport> airports = airportRepository.findAllSortedASC();
        assertTrue(airports != null);
    }
}
