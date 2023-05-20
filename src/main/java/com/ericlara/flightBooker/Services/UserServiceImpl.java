package com.ericlara.flightBooker.Services;

import java.util.Arrays;
import java.util.HashSet;

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

    // Inject the UserRepository
    @Autowired
    private UserRepository userRepository;

    // Inject the RoleRepository
    @Autowired
    private RoleRepository roleRepository;

    // Inject the PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Implement the register method
    @Override
    public void register(UserDto userDto) throws UserAlreadyExistsException {

        // Check if the user already exists
        if (checkIfUserExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User already exists for this email");
        }

        // Create a new Role object
        Role role = new Role(TbConstants.Roles.USER);

        // Save the Role object
        roleRepository.save(role);

        // Create a new UserEntity object
        UserEntity user = new UserEntity(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role),
                new HashSet<>()
        );

        // Save the UserEntity object
        userRepository.save(user);

    }

    // Implement the findUserByEmail method
    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Implement the checkIfUserExists method
    @Override
    public boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

}

