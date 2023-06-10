package com.ericlara.flightBooker.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

// Force entity and column names to be uppercase since H2 converts unquoted identifiers to uppercase.
// This enables creating a custom query for the Flight repository, and H2 will automatically convert them to uppercase.
// Reported on: https://stackoverflow.com/questions/10789994/make-h2-treat-quoted-name-and-unquoted-name-as-the-same
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "FLIGHT")
@Entity(name = "FLIGHT")
public class Flight {

    // Primary key for the flight table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FLIGHT_ID")
    private Long id;

    // The flight's origin
    @Column(name = "ORIGIN")
    private String origin;

    // The flight's destination
    @Column(name = "DESTINATION")
    private String destination;

    // The date of the flight's departure
    @Column(name = "DEPARTURE_DATE")
    private LocalDate departureDate;

    // The time of the flight's departure
    @Column(name = "DEPARTURE_TIME")
    private LocalTime departureTime;

    // The number of available seats on the flight
    @Column(name = "AVAILABLE_SEATS")
    private Integer availableSeats;

    // The flight number
    @Column(name = "FLIGHT_NUMBER")
    private String flightNumber;

    // The price of the flight
    @Column(name = "PRICE")
    private Double price;

    @OneToMany(mappedBy = "flight")
    @Default
    private Set<Booking> bookings = new HashSet<>();
    

    // Returns formatted departure time using a 12-hour clock pattern
    public String getFormattedDepartureTime() {
        String pattern = "hh:mm a";
        String time = departureTime.format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public void decrementAvailableSeats() {
        this.availableSeats--;
    }

    public void incrementAvailableSeats() {
        this.availableSeats++;
    }

    // Returns formatted price as a string
    public String getFormattedPrice() {
        return String.format("$%,.2f", price);
    }

    // Returns the destination address by removing the last six characters of the
    // string
    public String getDestinationAddress() {
        // int index = this.destination.indexOf(",");
        return this.destination.substring(0, destination.length() - 6);
    }


}
