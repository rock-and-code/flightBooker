package com.ericlara.flightBooker.Services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Repositories.RoleRepository;
import com.ericlara.flightBooker.Repositories.UserRepository;
import com.ericlara.flightBooker.util.TbConstants;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDto userDto) throws UserAlreadyExistsException {

        if(checkIfUserExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists for this email");
        }

        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        UserEntity user = new UserEntity(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role), new ArrayList<>());
       
        userRepository.save(user);

    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
