package com.ericlara.flightBooker.Controllers;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ericlara.flightBooker.Models.Flight;
import com.ericlara.flightBooker.Repositories.AirportRepository;
import com.ericlara.flightBooker.Repositories.FlightRepository;
import com.ericlara.flightBooker.Services.AirportService;
import com.ericlara.flightBooker.Services.CustomUserDetailService;
import com.ericlara.flightBooker.Services.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FlightRestController.class)
@AutoConfigureMockMvc(addFilters = false) //TO CIRCUMVENT SPRING SECURITY
@ExtendWith(MockitoExtension.class)
//@Import(BootstrapData.class)
public class FlightRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private CustomUserDetailService customUserDetailService;

  @MockBean
  private static FlightRepository flightRepository;

  @MockBean
  private static AirportRepository airportRepository;

  @MockBean(name = "flightService")
  private FlightService flightService;

  @MockBean(name = "airportService")
  private AirportService airportService;

  private static List<Flight> flights;

  @Captor
  private ArgumentCaptor<Long> longArgumentCaptor;

  @BeforeEach
  public void setUp() {
    flights = new ArrayList<>();
    //objectMapper.findAndRegisterModules();

    Flight newFlight = Flight.builder()
        .departureDate(LocalDate.now())
        .departureTime(LocalTime.now())
        .origin("San Juan, PR (SJR)")
        .destination("New York, NY (JFK)")
        .availableSeats(166)
        .flightNumber("MZ568922")
        .price(169.99)
        .build();
    flights.add(newFlight);

    Flight newFlight2 = Flight.builder()
        .departureDate(LocalDate.now())
        .departureTime(LocalTime.now())
        .origin("San Juan, PR (SJR)")
        .destination("New York, NY (JFK)")
        .availableSeats(166)
        .flightNumber("TY568922")
        .price(169.99)
        .build();
    flights.add(newFlight2);
  }

  @Test
  void testDeleteflightById() throws Exception {
    Flight flight = flights.get(0);
    flight.setId(Long.valueOf(1));
    mockMvc.perform(delete(FlightRestController.FLIGHT_PATH_ID, flight.getId())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andDo(MockMvcResultHandlers.print());
    //longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
    BDDMockito.verify(flightService).deleteFlightById(longArgumentCaptor.capture());

    assertEquals(flight.getId(), longArgumentCaptor.getValue());
  }

  @Test
  void testGetFlightById() throws Exception {

    Long flightId = new Random().nextLong(300);
    Flight flight = flights.get(0);
    BDDMockito.given(flightService.findFlightById(any(Long.class))).willReturn(flight);
    Mockito.when(flightService.findFlightById(flightId)).thenReturn(flight);

    mockMvc.perform(get(FlightRestController.FLIGHT_PATH_ID, flightId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.origin", is(flight.getOrigin().toString())))
        .andDo(MockMvcResultHandlers.print());

  }

  @Test
  void testGetFlights() throws Exception {
    Page<Flight> flightPage = new PageImpl<>(flights);
    BDDMockito.given(flightService.findFlightsByDepartureDate(any(), any(), any())).willReturn(flightPage);
    Mockito.when(flightService.findFlightsByDepartureDate(any(), any(), any())).thenReturn(flightPage);

    mockMvc.perform(get(FlightRestController.FLIGHT_PATH)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void testPatchFlightById() throws Exception {

    Flight flight = flights.get(1);
    flight.setId(Long.valueOf(1));

    mockMvc.perform(patch(FlightRestController.FLIGHT_PATH_ID, flight.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(flight))
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print());
    BDDMockito.verify(flightService).patchFlightById(any(Long.class),any(Flight.class));
  }

  @Test
  void testSaveFlight() throws Exception {

    // Long flightId = new Random().nextLong(300);
    Flight flight = flights.get(1);
    flight.setId(Long.valueOf(2));
    BDDMockito.given(flightService.saveFlight(any(Flight.class))).willReturn(flights.get(1));
    //.willAnswer(invocation->invocation.getArguments());
    // System.out.println(objectMapper.writeValueAsString(flight));
    mockMvc.perform(post(FlightRestController.FLIGHT_PATH)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8") //TO ADD JSON TO THE RESPONSE BOY
        //.contentType(objectMapper.writeValueAsString(flight)))
            .content(objectMapper.writeValueAsString(flight)))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andDo(MockMvcResultHandlers.print());
  }

  @Test
  void testUpdateFlightById() throws Exception {
    Flight flight = flights.get(0);
    flight.setId(Long.valueOf(1));

    mockMvc.perform(put(FlightRestController.FLIGHT_PATH_ID, flight.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(flight))
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print());
    BDDMockito.verify(flightService).updateFlightById(any(Long.class),any(Flight.class));
  }
}
