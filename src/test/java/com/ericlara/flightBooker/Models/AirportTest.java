package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AirportTest {
    @Test
    void getCityShouldReturnNewYork() {
        Airport instance = new Airport();
        instance.setCity("New York");
        String expResult = "New York";
        String result = instance.getCity();
        assertEquals(expResult, result);
    }

    @Test
    void equalsShouldReturnTrue() {
        Airport instance = Airport.builder().name("Birmingham–Shuttlesworth International Airport").city("Birmingham").state("Alabama").iataId("BHM").build();
        Airport anotherInstance = Airport.builder().name("Birmingham–Shuttlesworth International Airport").city("Birmingham").state("Alabama").iataId("BHM").build();
        boolean result = instance.equals(anotherInstance);
        assertTrue(result);
    }

    @Test
    void testGetFormattedLocation() {
        Airport instance = Airport.builder().name("Fairbanks International Airport").city("Fairbanks").state("Alaska").iataId("FAI").build();
        String expResult = instance.getCity() + ", " + instance.getState() + " (" + instance.getIataId() + ")";
        String result = instance.getFormattedLocation();
        assertEquals(expResult, result);
    }

    @Test
    void getIdShouldReturn1L() {
        Airport instance = Airport.builder().id(1L).name("Homer Airport").city("Homer").state("Alaska").iataId("HOM").build();
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void getIataIdShouldReturnHOM() {
        Airport instance = Airport.builder().id(1L).name("Homer Airport").city("Homer").state("Alaska").iataId("HOM").build();
        String expResult = "HOM";
        String result = instance.getIataId();
        assertEquals(expResult, result);
    }

    @Test
    void getNameShouldReturnHomerAirport() {
        Airport instance = Airport.builder().id(1L).name("Homer Airport").city("Homer").state("Alaska").iataId("HOM").build();
        String expResult = "Homer Airport";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    void getStateShouldReturnAlaska() {
        Airport instance = Airport.builder().id(1L).name("Homer Airport").city("Homer").state("Alaska").iataId("HOM").build();
        String expResult = "Alaska";
        String result = instance.getState();
        assertEquals(expResult, result);
    }

}
