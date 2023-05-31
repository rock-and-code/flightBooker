package com.ericlara.flightBooker.Services;

import java.util.List;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserNotFoundException;
import com.ericlara.flightBooker.Models.UserXML;

public interface UserXMLService {

    // Register a new user
    void register(UserXML userXML) throws UserAlreadyExistsException;

    // Find a user by email
    UserXML findUserByEmail(String email);

    // Find a user by email
    UserXML findUserById(Long id) throws UserNotFoundException;


    List<UserXML> findAllUsers();

    // Check if a user exists by email
    boolean checkIfUserExists(final String email);

}