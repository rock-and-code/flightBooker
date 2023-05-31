package com.ericlara.flightBooker.Services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Mappers.UserMapper;
import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Models.UserNotFoundException;
import com.ericlara.flightBooker.Models.UserXML;
import com.ericlara.flightBooker.Repositories.RoleRepository;
import com.ericlara.flightBooker.Repositories.UserRepository;
import com.ericlara.flightBooker.util.TbConstants;

@Service("userXMLService")
public class UserXMLServiceImpl implements UserXMLService {

    // Inject the UserRepository
    @Autowired
    private UserRepository userRepository;

    // Inject the RoleRepository
    @Autowired
    private RoleRepository roleRepository;

    // Inject the PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    // Implement the register method
    @Override
    public void register(UserXML userXML) throws UserAlreadyExistsException {

        // Check if the user already exists
        if (checkIfUserExists(userXML.getEmail())) {
            throw new UserAlreadyExistsException("User already exists for this email");
        }

       // Create a new Role object
       Role role = roleRepository.findByName(TbConstants.Roles.USER);

    //    System.out.println(role.getName());

        // Create a new UserEntity object
        UserEntity user = new UserEntity(
                userXML.getFirstName(),
                userXML.getLastName(),
                userXML.getEmail(),
                passwordEncoder.encode(userXML.getPassword()),
                Arrays.asList(role),
                new HashSet<>()
        );

        // Save the UserEntity object
        userRepository.save(user);

    }

    // Implement the findUserByEmail method
    @Override
    public UserXML findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return userMapper.userEntityToUserXML(userEntity);
    }

    @Override
    public UserXML findUserById(Long id) throws UserNotFoundException {

        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException();
        } else {
            UserEntity userEntity = userRepository.findById(id).get();
            return userMapper.userEntityToUserXML(userEntity);
        }
    }

    // Implement the checkIfUserExists method
    @Override
    public boolean checkIfUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public List<UserXML> findAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAllUsers();
        return userMapper.userEntityListToUserXMLList(userEntityList);
    }

}

