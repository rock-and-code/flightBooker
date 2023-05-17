package com.ericlara.flightBooker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ericlara.flightBooker.Models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    /**
     * Returns a role by name.
     *
     * @param name The name of the role to find.
     * @return A role with the given name.
     */
    Role findByName(String name);

}
