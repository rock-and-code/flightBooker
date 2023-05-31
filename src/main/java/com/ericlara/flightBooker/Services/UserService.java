package com.ericlara.flightBooker.Services;

import java.util.List;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;

public interface UserService {

    // Register a new user
    void register(UserDto userDto) throws UserAlreadyExistsException;

    // Find a user by email
    UserEntity findUserByEmail(String email);

    //Find all users
    List<UserEntity> findAllUsers();

    // Check if a user exists by email
    boolean checkIfUserExists(final String email);

}

