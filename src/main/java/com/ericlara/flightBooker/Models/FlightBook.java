package com.ericlara.flightBooker.Models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "FLIGHT_BOOK")
@Table(name = "FLIGHT_BOOK")
public class FlightBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOOKING_NUMBER")
    private UUID bookingNumber;

    @Column(name = "BOOKED_DATE")
    private LocalDate bookedDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;


    public Long getId() {
        return id;
    }

    public FlightBook() {}

    public FlightBook(UUID bookingNumber, LocalDate bookedDate, UserEntity user, Flight flight) {
        this.bookingNumber = bookingNumber;
        this.bookedDate = bookedDate;
        this.user = user;
        this.flight = flight;
    }

    public UUID getBookingNumber() {
        return bookingNumber;
    }
    public void setBookingNumber(UUID bookNumber) {
        this.bookingNumber = bookNumber;
    }
    
    public LocalDate getBookedDate() {
        return bookedDate;
    }
    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }


}
