package com.ericlara.flightBooker.Controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Services.AirportService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;
@WebMvcTest(FlightController.class)
// This annotation tells Spring Boot to create a MockMvc instance for testing the FlightController class.

@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
// This annotation tells Spring Boot to not add any Spring Security filters to the MockMvc instance. This is necessary because we are testing the FlightController class without Spring Security enabled.

@ExtendWith(MockitoExtension.class)
// This annotation tells JUnit to use the Mockito framework for mocking dependencies.

public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // This field is injected by Spring Boot. It provides a MockMvc instance that can be used to test the FlightController class.

    @MockBean
    private AuthenticationManager authenticationManager;
    // This field is mocked by Mockito. It provides a mock implementation of the AuthenticationManager interface. This is necessary because we are testing the FlightController class without Spring Security enabled.

    @MockBean(name = "flightService")
    private FlightService flightService;
    // This field is mocked by Mockito. It provides a mock implementation of the FlightService interface. This is necessary because we want to test the FlightController class in isolation.

    @MockBean(name = "airportService")
    private AirportService airportService;
    // This field is mocked by Mockito. It provides a mock implementation of the AirportService interface. This is necessary because we want to test the FlightController class in isolation.

    @MockBean(name = "userService")
    private UserService userService;
    // This field is mocked by Mockito. It provides a mock implementation of the UserService interface. This is necessary because we want to test the FlightController class in isolation.

    @Test
    void testFlightsForm() throws Exception {
        // This method tests the FlightController.flightsForm() method.

        MockHttpServletRequest request = new MockHttpServletRequest();
        // This creates a new MockHttpServletRequest object. This request will be used to simulate a user request to the FlightController.flightsForm() method.

        //CREATES A USER FOR DEMOSTRATION PURPOSES ONLY, THE APP ALLOWS NEW USERS TO BE REGISTERED
        UserDto user = new UserDto("userFirstName", "userLastName", "user@email.com", "password");
        // This creates a new UserDto object. This user will be used to simulate a user who is logged in to the application.

        //SAVES USER TO THE MEMORY DBA
        userService.register(user);
        // This saves the user to the in-memory database. This is necessary because we want to simulate a user who is logged in to the application.

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        authToken.setDetails(new WebAuthenticationDetails(request));
        // This creates a new UsernamePasswordAuthenticationToken object. This object will be used to simulate a user who is logged in to the application.

        Authentication authentication = authenticationManager.authenticate(authToken);
        // This authenticates the user. This is necessary because we want to simulate a user who is logged in to the application.

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // This sets the current security context to the authenticated user. This is necessary because we want to simulate a user who is logged in to the application.

        this.mockMvc.perform(get("/")
        ).andExpect(view().name("flights/flightSearchForm"))
        .andDo(MockMvcResultHandlers.print());
        // This performs a GET request to the "/" URL. This request is expected to return the "flights/flightSearchForm" view. The MockMvcResultHandlers.print() method is used to print the results of the request.

    }

}

