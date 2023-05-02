package com.ericlara.flightBooker.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.ericlara.flightBooker.Models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
