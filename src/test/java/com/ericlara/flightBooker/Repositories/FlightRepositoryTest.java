package com.ericlara.flightBooker.Repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ericlara.flightBooker.Models.Airport;
import com.ericlara.flightBooker.Models.AirportData;
import com.ericlara.flightBooker.Models.Flight;

@DataJpaTest
public class FlightRepositoryTest {
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;

    private List<Airport> airports;

    @BeforeEach
    void setUp() {
        airports = new ArrayList<>();
        Random random = new Random();
        int RANDOM_AIRPORTS_SAMPLE = 6;
        int RANDOM_FLIGHTS_SAMPLE = 18;
        // Generate a list of Airports randomly from a list (CLASS AIRPORTDATA ON
        // DOMAINS FOLDER)
        for (int i = 0; i < RANDOM_AIRPORTS_SAMPLE; ++i) {
            int LIMIT = AirportData.usAirports.size();
            int index = random.nextInt(LIMIT);
            Airport randAirport = AirportData.usAirports.get(index);
            // Checking whether the randomly created aiport is not in the list
            // To avoid creating and saving duplicates airport in the DBA
            if (!airports.contains(randAirport)) {
                // ADDING AIRPORT TO DATABASE
                Airport randAirportSaved = airportRepository.save(randAirport);
                // ADDING AIRPORT TO LIST TO GENERATE FLIGHTS
                airports.add(randAirportSaved);
            }

        }

        // Generate a list of flights randomly
        for (int i = 0; i < RANDOM_FLIGHTS_SAMPLE; ++i) {
            int airplaneSeats = 166; // BASED ON THE CAPACITY OF BOEING 737 WHICH IS THE MOST COMMON AIRPLANE
            // SET A LIMIT TO DEPARTURE DATE YEAR
            int year = 2023;
            // SET DATES FROM LOCAL DATE TO DECEMBER SINCE WE SHOULD NOT SEARCH FLIGHTS FOR
            // PAST DATES
            LocalDate today = LocalDate.now();
            int month = random.nextInt(today.getMonthValue(), 13);
            int day = (month == 2) ? random.nextInt(1, 29) : random.nextInt(1, 31);
            LocalDate departureDate = LocalDate.of(year, month, day);
            // DEPARTURE TIME
            int hour = random.nextInt(1, 24);
            int minutes = random.nextInt(0, 60);
            LocalTime departureTime = LocalTime.of(hour, minutes);

            int origin = random.nextInt(airports.size()); // integer to get a random airport from the airport list
            int destination = 0;
            do { // while loop to avoid same airport for to and from flight
                destination = random.nextInt(airports.size()); // integer to get a random airport from the airport list
            } while (destination == origin);

            // GETTING ORIGIN AND DESTINATION AIRPORTS ID TO CREATE A FLIGHT NUMBER
            String originAirportId = String.valueOf(airports.get(origin).getId());
            String destinationAirportId = String.valueOf(airports.get(destination).getId());

            // FLIGHT NUMBER (2 CHAR AND 6 DIGITS): CHAR 1-2 (A-Z), DIGITS 1-2 SUM OF
            // FLIGHT'S DATE (YYYY-MM-DD),
            // DIGITS 3-4 AIRPORT OF ORIGIN ID, DIGITS 5-6 AIRPORT OF DESTINATION
            Character firstCharacter = (char) ('A' + (random.nextInt(26)));
            Character secondCharacter = (char) ('A' + (random.nextInt(26)));
            String flightNumber = String.valueOf(firstCharacter) + String.valueOf(secondCharacter)
                    + String.valueOf((year + month + day) % 100) + String.valueOf(originAirportId)
                    + String.valueOf(destinationAirportId);

            double price = random.nextDouble(80.00, 450.00);

            // Creating and saving flight in the DBA
            Flight flight = Flight.builder()
                    .departureDate(departureDate)
                    .departureTime(departureTime)
                    .origin(airports.get(origin).getFormattedLocation())
                    .destination(airports.get(destination).getFormattedLocation())
                    .availableSeats(random.nextInt(20, airplaneSeats + 1)) // TO ENSURE AT LEAST 20 AVAILABLES SEATS ON
                                                                           // EVERY GENERATED FLIGHT
                    .flightNumber(flightNumber)
                    .price(price)
                    .build();

            // SAVING FLIGHT IN THE DBA
            flightRepository.save(flight);
        }

        Flight flight = Flight.builder()
                .departureDate(LocalDate.now())
                .departureTime(LocalTime.now())
                .origin(airports.get(1).getFormattedLocation())
                .destination(airports.get(2).getFormattedLocation())
                .availableSeats(20)
                .flightNumber("TT0945")
                .price(129.22)
                .build();

        // SAVING FLIGHT IN THE DBA
        flightRepository.save(flight);
    }

    @Test
    void testFindByDepartureDate() {
        List<Flight> flights = flightRepository.findByDepartureDate(LocalDate.now(), null);
        assertTrue(flights.size() > 0);
    }

    @Test
    void testFindByOriginDepartureDateSeatsAvailable() {
        List<Flight> flights = flightRepository.findByOriginDepartureDateSeatsAvailable(airports.get(1).getFormattedLocation(), LocalDate.now(), 20);
        assertTrue(flights.size() > 0);
    }

    @Test
    void testFindByOriginDestinationDepartureDateSeatsAvailable() {
        List<Flight> flights = flightRepository.findByOriginDestinationDepartureDateSeatsAvailable(airports.get(1).getFormattedLocation(),
        airports.get(2).getFormattedLocation(), LocalDate.now(), 20);
        assertTrue(flights.size() > 0);
    }

    @Test
    void testFindFromDepartureDate() {
        List<Flight> flights = flightRepository.findFromDepartureDate(LocalDate.now(), null);
        assertTrue(flights.size() > 0);
    }
}
