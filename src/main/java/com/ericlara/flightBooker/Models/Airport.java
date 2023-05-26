package com.ericlara.flightBooker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * Represents an airport.
 * 
 * Based on information from Wikipedia
 * 
 * source: https://en.wikipedia.org/wiki/List_of_airports_in_the_United_States
 */
@Data
@NoArgsConstructor
@Table(name = "AIRPORT")
@Entity(name = "AIRPORT")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "LOCATION_ID")
    private String locationId;

    //Returns a formatted location string with the city, state, and location ID
    public String getFormattedLocation() {
        return this.city + ", " + this.state + " (" + this.locationId + ")";
    }

    //Custome Builder pattern for creating an Airport object
    public Airport name(String name) {
        this.name = name;
        return this;
    }

    public Airport city(String city) {
        this.city = city;
        return this;
    }

    public Airport state(String state) {
        this.state = state;
        return this;
    }

    public Airport locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

}
