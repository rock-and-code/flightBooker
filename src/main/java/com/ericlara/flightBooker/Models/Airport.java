package com.ericlara.flightBooker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * Represents an airport.
 * 
 * Based on information from Wikipedia
 * 
 * source: https://en.wikipedia.org/wiki/List_of_airports_in_the_United_States
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    //Returns a formatted location string with the city, state, and location ID
    public String getFormattedLocation() {
        return this.city + ", " + this.state + " (" + this.locationId + ")";
    }
    //Returns the hash code for this object
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((locationId == null) ? 0 : locationId.hashCode());
        return result;
    }
    //Returns true if the object being compared is the same object, or if it has the same ID and location ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Airport other = (Airport) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (locationId == null) {
            if (other.locationId != null)
                return false;
        } else if (!locationId.equals(other.locationId))
            return false;
        return true;
    }
    //Returns a string representation of this object
    @Override
    public String toString() {
        return "Airport [id=" + id + ", name=" + name + ", city=" + city + ", state=" + state + ", locationId="
                + locationId + "]";
    }

    //Builder pattern for creating an Airport object
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
