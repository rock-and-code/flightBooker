package com.ericlara.flightBooker.Models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "FLIGHT_BOOK")
@Table(name = "FLIGHT_BOOK")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOOKING_NUMBER")
    private UUID bookingNumber;

    @Column(name = "BOOKED_DATE")
    private LocalDate bookedDate;

    @ManyToOne
    //@JoinColumn(name = "USER_ID")
    private UserEntity user;

    @ManyToOne
    //@JoinColumn(name = "FLIGHT_ID")
    private Flight flight;

    public Booking(UUID bookingNumber, LocalDate bookedDate, UserEntity user, Flight flight) {
        this.bookingNumber = bookingNumber;
        this.bookedDate = bookedDate;
        this.user = user;
        this.flight = flight;
    }

}
