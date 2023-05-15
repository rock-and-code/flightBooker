package com.ericlara.flightBooker.Repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ericlara.flightBooker.Models.UserEntity;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void saveUser() {
        UserEntity userEntity = new UserEntity("User", "UserLastName", "user@email.com", "password", null, null);
        UserEntity savedUser = userRepository.save(userEntity);
        assertTrue(savedUser != null);
    }
    @Test
    void testFindByEmail() {
        UserEntity userEntity = new UserEntity("User", "UserLastName", "user@email.com", "password", null, null);
        userRepository.save(userEntity);
        UserEntity savedUser = userRepository.findByEmail("user@email.com");
        assertTrue(savedUser != null);
    }
}
