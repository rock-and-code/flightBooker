package com.ericlara.flightBooker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericlara.flightBooker.Models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
