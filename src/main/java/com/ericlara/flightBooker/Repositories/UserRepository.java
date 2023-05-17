package com.ericlara.flightBooker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ericlara.flightBooker.Models.UserEntity;
@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Returns a user by email.
     *
     * @param email The email of the user to find.
     * @return A user with the given email.
     */
    UserEntity findByEmail(String email);

}
 