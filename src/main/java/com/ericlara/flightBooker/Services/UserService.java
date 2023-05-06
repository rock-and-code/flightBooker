package com.ericlara.flightBooker.Services;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;

public interface UserService {
    void register(UserDto userDto) throws UserAlreadyExistsException;
    UserEntity findUserByEmail(String email);
    boolean checkIfUserExists(final String email);

    
}
