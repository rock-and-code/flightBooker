package com.ericlara.flightBooker.Services;

import com.ericlara.flightBooker.Models.User;
import com.ericlara.flightBooker.Models.UserDto;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
