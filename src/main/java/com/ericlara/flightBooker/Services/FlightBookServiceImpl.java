package com.ericlara.flightBooker.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericlara.flightBooker.Models.FlightBook;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Repositories.FlightBookRepository;
@Service("flightBookService")
public class FlightBookServiceImpl implements FlightBookService {

    @Autowired
    private FlightBookRepository flightBookRepository;    

    @Override
    public Optional<FlightBook> findById(Long id) {
        return flightBookRepository.findById(id);
    }

    @Override
    public void save(FlightBook flightBook) {
        flightBookRepository.save(flightBook);
    }

    @Override
    public Iterable<FlightBook> findAllByUser(UserEntity user) {
        return flightBookRepository.findAllByUser(user);
    }

    @Override
    public void delete(FlightBook flightBook) {
        flightBookRepository.delete(flightBook);
    }



    
}
 