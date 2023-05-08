package com.ericlara.flightBooker.Services;

import java.util.Optional;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;

public interface FlightBookService {

    public Optional<FlightBook> findById(Long id);

    public void save(FlightBook flightBook);
    
    //public Iterable<FlightBook> findByFlight(Flight flight);

    public Iterable<FlightBook> findAllByUser(UserEntity user);

    public void delete(FlightBook flightBook);
}
