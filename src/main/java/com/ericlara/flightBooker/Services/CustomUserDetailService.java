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
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER").build();

        return userDetails;
    }
    
}
