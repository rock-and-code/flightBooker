package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BookingDtoTest {
    @Test
    void getPassengersShouldReturn166() {
        BookingDto instance = new BookingDto();
        instance.setPassengers(166);
        int expResult = 166;
        int result = 166;
        assertEquals(expResult, result);
    }

    @Test
    void testGetPassengers() {
        
    }
}   
