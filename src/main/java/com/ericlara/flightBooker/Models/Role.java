package com.ericlara.flightBooker.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "ROLES")
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="NAME", nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @Column(name="USERS", nullable = false)
    private List<User> users = new ArrayList<>();

    public Role(){}
    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}