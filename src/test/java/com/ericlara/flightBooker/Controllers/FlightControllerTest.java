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
@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean(name = "flightService")
    private FlightService flightService;
    @MockBean(name = "airportService")
    private AirportService airportService;
    @MockBean(name = "userService")
    private UserService userService;
    @Test
    void testFlightsForm() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        //CREATES A USER FOR DEMOSTRATION PURPOSES ONLY, THE APP ALLOWS NEW USERS TO BE REGISTERED
        UserDto user = new UserDto("userFirstName", "userLastName", "user@email.com", "password");
        //SAVES USER TO THE MEMORY DBA
        userService.register(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        authToken.setDetails(new WebAuthenticationDetails(request));
        
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.mockMvc.perform(get("/")
        ).andExpect(view().name("flights/flightSearchForm"))
        .andDo(MockMvcResultHandlers.print());

    }
}
