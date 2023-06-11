package com.ericlara.flightBooker.Controllers;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.UserService;
import com.ericlara.flightBooker.util.TbConstants;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

@WebMvcTest(RegistrationController.class)

@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY

@ExtendWith(MockitoExtension.class)
// This annotation tells JUnit to use the Mockito framework for mocking
// dependencies.
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // This field is injected by Spring Boot. It provides a MockMvc instance that
    // can be used to test the FlightController class.

    @MockBean
    private AuthenticationManager authenticationManager;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // AuthenticationManager interface. This is necessary because we are testing the
    // FlightController class without Spring Security enabled.

    @MockBean(name = "userService")
    private UserService userService;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // UserService interface. This is necessary because we want to test the
    // FlightController class in isolation.

    private PasswordEncoder passwordEncoder;
    private UserEntity user;

    @BeforeEach
    void setUp() throws UserAlreadyExistsException {
        passwordEncoder = new BCryptPasswordEncoder();
        //Creating a role for the booking user
        Role role = new Role(TbConstants.Roles.USER);
        //Creating a user for booking
        user = new UserEntity("userFirstName", 
            "userLastName", 
            "user@email.com",
            passwordEncoder.encode("password"),
            Arrays.asList(role), 
            new HashSet<>());
    
    }
    

    @Test
    void testRegister() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(view().name("authentication/register"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "user@email.com", authorities = { "ROLE_USER" })
    void testUserRegistration() throws Exception {
        UserDto userDto  = new UserDto("userFirstName",
                "userLastName",
                "user@email.com",
                "password");

        String userName = "user@email.com";

        //To address custom authentication method in Booking controller 
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(user);
        Mockito.when(userService.findUserByEmail(userName)).thenReturn(user);

        this.mockMvc.perform(post("/register")
                .flashAttr("userDto", userDto)
                .with(csrf())
                ).andExpect(view().name("redirect:/?successRegistration"))
                .andExpect(status().is(302))
                .andDo(MockMvcResultHandlers.print());

    }
}
