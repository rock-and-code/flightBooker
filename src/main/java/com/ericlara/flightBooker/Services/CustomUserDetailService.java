package com.ericlara.flightBooker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Repositories.UserRepository;

@Service("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    // Inject the UserRepository
    @Autowired
    private UserRepository userRepository;

    // Implement the loadUserByUsername method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Get the user from the database by email
        UserEntity user = userRepository.findByEmail(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        // Create a UserDetails object from the user entity
        UserDetails userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER").build();

        // Return the UserDetails object
        return userDetails;
    }

}
