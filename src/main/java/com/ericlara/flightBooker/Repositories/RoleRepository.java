package com.ericlara.flightBooker.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.ericlara.flightBooker.Models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByName(String name);
}
