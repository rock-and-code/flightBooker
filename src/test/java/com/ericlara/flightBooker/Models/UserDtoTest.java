package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDtoTest {

    private UserDto instance;

    @BeforeEach
    void setUp() {

        // Creating new user
        instance = new UserDto("userFirstName",
                "userLastName",
                "user@email.com",
                "password");
    }
    @Test
    void testEquals() {
        UserDto anotherInstance = new UserDto("userFirstName",
        "userLastName",
        "user@email.com",
        "password");
        boolean expResult = true;
        boolean result = instance.equals(anotherInstance);
        assertEquals(expResult, result);
    }

    @Test
    void testGetEmail() {
        String expResult = "user@email.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    @Test
    void testGetFirstName() {
        String expResult = "userFirstName";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    @Test
    void testGetLastName() {
        String expResult = "userLastName";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    @Test
    void testGetPassword() {
        String expResult = "password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }
}
