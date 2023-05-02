package com.ericlara.flightBooker.Models;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Column(name = "ID")
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

// Returns the destination address by removing the last six characters of the string 
    public String getDestinationAddress() {
        //int index = this.destination.indexOf(",");
        return this.destination.substring(0, destination.length()-6);
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
    
    //Create a constructor following the builder approach for better readability
    public Flight origin(String origin) {
        this.origin = origin;
        return this;
    }

    public Flight destination(String destination) {
        this.destination = destination;
        return this;
    }

    public Flight departureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public Flight departureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public Flight availableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
        return this;
    }
    
    public Flight flightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }
    public Flight price(double price) {
        this.price = price;
        return this;
    }


    
    
}
