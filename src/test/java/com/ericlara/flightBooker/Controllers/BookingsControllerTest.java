package com.ericlara.flightBooker.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import com.ericlara.flightBooker.Models.AirportData;
import com.ericlara.flightBooker.Models.BookingDto;
import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Models.Role;
import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.FlightBookService;
import com.ericlara.flightBooker.Services.FlightService;
import com.ericlara.flightBooker.Services.UserService;
import com.ericlara.flightBooker.util.TbConstants;

@WebMvcTest(BookingsController.class)
//@AutoConfigureMockMvc(addFilters = false) // TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
public class BookingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;
    
    @MockBean
    private TestRestTemplate restTemplate;

    @MockBean(name = "userService")
    private UserService userService;

    @MockBean(name = "flightService")
    private FlightService flightService;

    @MockBean(name = "flightBookService")
    private FlightBookService flightBookService;

    private PasswordEncoder passwordEncoder;
    private Flight flight;
    private UserEntity user;

    @BeforeEach
    void setUp() throws UserAlreadyExistsException {
        passwordEncoder = new BCryptPasswordEncoder();
        //Creating a flight for booking
        flight = Flight.builder()
        .id(1L)
        .departureDate(LocalDate.of(2022, 05, 30))
        .departureTime(LocalTime.of(12, 30, 0))
        .origin(AirportData.usAirports.get(0).getFormattedLocation())
        .destination(AirportData.usAirports.get(1).getFormattedLocation())
        .availableSeats(50)
        .flightNumber("MX6789")
        .price(150.45)
        .build();
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

    /**
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "user@email.com", authorities = { "ROLE_USER" })
    public void checkoutFormTest() throws Exception {

        String userName = "user@email.com";
        Long flightId = 1L;
        BDDMockito.given(flightService.findFlightById(any(Long.class))).willReturn(flight);
        Mockito.when(flightService.findFlightById(flightId)).thenReturn(flight);

        //To address custom authentication method in Booking controller 
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(user);
        Mockito.when(userService.findUserByEmail(userName)).thenReturn(user);

        // this.mockMvc.
        this.mockMvc.perform(get("/bookings/flights/{id}", 1L))
                .andExpect(view().name("bookings/bookingCheckoutForm"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "user@email.com", authorities = { "ROLE_USER" })
    void testBookFlight() throws Exception {
    

        BookingDto bookingDto = new BookingDto();
        bookingDto.setPassengers(2);

        String userName = "user@email.com";
        Long flightId = 1L;
        BDDMockito.given(flightService.findFlightById(any(Long.class))).willReturn(flight);
        Mockito.when(flightService.findFlightById(flightId)).thenReturn(flight);

        //To address custom authentication method in Booking controller 
        BDDMockito.given(userService.findUserByEmail(any(String.class))).willReturn(user);
        Mockito.when(userService.findUserByEmail(userName)).thenReturn(user);


        this.mockMvc.perform(post("/bookings/flights/{id}", 1L)
                .flashAttr("bookingDto", bookingDto)
                .with(csrf())
                ).andExpect(view().name("redirect:/?successBooking"))
                .andExpect(status().is(302))
                .andDo(MockMvcResultHandlers.print());

    }
}
