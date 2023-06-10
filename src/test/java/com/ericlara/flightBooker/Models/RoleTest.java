package com.ericlara.flightBooker.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ericlara.flightBooker.util.TbConstants;

public class RoleTest {

    private UserEntity user;
    private PasswordEncoder passwordEncoder;
    private Role instance;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        instance = new Role(TbConstants.Roles.USER);
        instance.setId(1L);
        user = new UserEntity("userFirstName", 
            "userLastName", 
            "user@email.com",
            passwordEncoder.encode("password"),
            Arrays.asList(instance), 
            new HashSet<>());
        instance.setUsers(List.of(user));
    }

    @Test
    void testGetId() {
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    void testGetName() {
        String expResult = "ROLE_USER";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    void testGetUsers() {
        List<UserEntity> expResult = List.of(user);
        List<UserEntity> result = instance.getUsers();
        assertEquals(expResult, result);
    }
}
