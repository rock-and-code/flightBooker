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

// Force entity and column names to be uppercase since H2 converts unquoted identifiers to uppercase.
// This enables creating a custom query for the Flight repository, and H2 will automatically convert them to uppercase.
// Reported on: https://stackoverflow.com/questions/10789994/make-h2-treat-quoted-name-and-unquoted-name-as-the-same

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
    private Set<FlightBook> bookings = new HashSet<>();
    
    public Flight(){}

    public Flight(Builder builder) {
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.departureDate = builder.departureDate;
        this.departureTime = builder.departureTime;
        this.availableSeats = builder.availableSeats;
        this.flightNumber = builder.flightNumber;
        this.price = builder.price;
        this.bookings = builder.bookings;
    }

    // Getters and setters for the class fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // Returns formatted departure time using a 12-hour clock pattern
    public String getFormattedDepartureTime() {
        String pattern = "hh:mm a";
        String time = departureTime.format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<FlightBook> getBookings() {
        return bookings;
    }

    public void setBookings(Set<FlightBook> bookings) {
        this.bookings = bookings;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + availableSeats;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Flight other = (Flight) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (departureDate == null) {
            if (other.departureDate != null)
                return false;
        } else if (!departureDate.equals(other.departureDate))
            return false;
        if (availableSeats != other.availableSeats)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", from=" + origin + ", to=" + destination + ", departureDate=" + departureDate
                + ", availableSeats=" + availableSeats + "]";
    }

    // Create a constructor following the builder approach for better readability
    public static class Builder {

        private String origin;
        private String destination;
        private LocalDate departureDate;
        private LocalTime departureTime;
        private Integer availableSeats;
        private String flightNumber;
        private Double price;
        private Set<FlightBook> bookings = new HashSet<>();

        // public static Builder newInstance() {
        //     return new Builder();
        // }

        // private Builder() {}

        public Builder(){}

        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder departureDate(LocalDate departureDate) {
            this.departureDate = departureDate;
            return this;
        }

        public Builder departureTime(LocalTime departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public Builder availableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public Builder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder bookings(Set<FlightBook> bookings) {
            this.bookings = bookings;
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }
    }


}
