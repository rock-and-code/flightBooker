package com.ericlara.flightBooker.Models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO TO HANDLE DATA FROM SEARCH FLIGHT FORM
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDto {

    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int numOfPassengers;
    
}
