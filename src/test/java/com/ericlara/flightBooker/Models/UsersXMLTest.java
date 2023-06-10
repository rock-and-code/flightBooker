package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersXMLTest {

    private UserXML userOne;
    private UserXML userTwo;
    private UsersXML users;

    @BeforeEach
    void setUp() {

        // Creating new user
        userOne = new UserXML("userFirstName",
                "userLastName",
                "user@email.com",
                "password");

        userTwo = new UserXML("userTwoFirstName",
                "userTwoLastName",
                "userTwo@email.com",
                "password");
        users = new UsersXML(List.of(userOne, userTwo));
    }

    @Test
    void testGetUsers() {
        List<UserXML> expResult = List.of(userOne, userTwo);
        List<UserXML> result = users.getUsers();
        assertEquals(expResult, result);
    }
}
