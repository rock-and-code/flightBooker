package com.ericlara.flightBooker.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.ericlara.flightBooker.Models.FlightBook;

public interface FlightBookRepository extends CrudRepository<FlightBook, Long> {
}
