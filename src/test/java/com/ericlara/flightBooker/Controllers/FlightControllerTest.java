package com.ericlara.flightBooker.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;

import org.mockito.BDDMockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.Mockito;
import com.ericlara.flightBooker.Models.AirportData;
import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.FlightDto;
import com.ericlara.flightBooker.Services.AirportService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;

@WebMvcTest(FlightController.class)
// This annotation tells Spring Boot to create a MockMvc instance for testing
// the FlightController class.

@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
// This annotation tells Spring Boot to not add any Spring Security filters to
// the MockMvc instance. This is necessary because we are testing the
// FlightController class without Spring Security enabled.

@ExtendWith(MockitoExtension.class)
// This annotation tells JUnit to use the Mockito framework for mocking
// dependencies.

public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // This field is injected by Spring Boot. It provides a MockMvc instance that
    // can be used to test the FlightController class.

    @MockBean
    private AuthenticationManager authenticationManager;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // AuthenticationManager interface. This is necessary because we are testing the
    // FlightController class without Spring Security enabled.

    @MockBean(name = "flightService")
    private FlightService flightService;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // FlightService interface. This is necessary because we want to test the
    // FlightController class in isolation.

    @MockBean(name = "airportService")
    private AirportService airportService;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // AirportService interface. This is necessary because we want to test the
    // FlightController class in isolation.

    @MockBean(name = "userService")
    private UserService userService;
    // This field is mocked by Mockito. It provides a mock implementation of the
    // UserService interface. This is necessary because we want to test the
    // FlightController class in isolation.

    @Test
    void testFlightsForm() throws Exception {
    
        // Perform the request and assert that the view name is correct.
        this.mockMvc.perform(get("/")).andExpect(view().name("flights/flightSearchForm"))
                .andDo(MockMvcResultHandlers.print());
        // This performs a GET request to the "/" URL. This request is expected to
        // return the "flights/flightSearchForm" view. The MockMvcResultHandlers.print()
        // method is used to print the results of the request.
    }

    @Test
    void searchResultsTest() throws Exception {
        // Set up the mock data.
        FlightDto flightDto = FlightDto.builder()
        .departureDate(LocalDate.of(2022, 05, 30))
        .destination(AirportData.usAirports.get(1).getFormattedLocation())
        .origin(AirportData.usAirports.get(0).getFormattedLocation())
        .numOfPassengers(3)
        .build();
        // Perform the request and assert that the view name is correct.
        this.mockMvc.perform(post("/flights")
                .flashAttr("flightDto", flightDto)
                ).andExpect(view().name("flights/flights"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getSearchResultsTest() throws Exception {
       // Perform the request and assert that the view name is correct.
        this.mockMvc.perform(get("/flights")
                .param("origin", "origin")
                .param("destination", "destination")
                .param("departureDate", "2023-05-30")
                ).andExpect(view().name("flights/flights"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testgetFlightDetails() throws Exception {
        // Set up the mock data.
        Long flightId = 1L;
        Flight flight = Flight.builder()
        .id(flightId)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(AirportData.usAirports.get(0).getFormattedLocation())
        .destination(AirportData.usAirports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
        // Mock the service findFlightById metod call in the controller.
        BDDMockito.given(flightService.findFlightById(any(Long.class))).willReturn(flight);
        Mockito.when(flightService.findFlightById(flightId)).thenReturn(flight);

        // Perform the request and assert that the view name is correct.
        this.mockMvc.perform(get("/flights/{flightId}", 1L))
                .andExpect(view().name("flights/flightDetails"))
                .andDo(MockMvcResultHandlers.print());
    }

}
