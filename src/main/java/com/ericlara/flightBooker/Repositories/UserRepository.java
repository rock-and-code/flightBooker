package com.ericlara.flightBooker.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.ericlara.flightBooker.Models.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
 