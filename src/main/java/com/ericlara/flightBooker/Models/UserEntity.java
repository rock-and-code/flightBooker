package com.ericlara.flightBooker.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USER")
@Table(name = "USER")
@Scope("session")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name="LAST_NAME", nullable = false)
    private String lastName;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    ) 
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user") //MAPPED BY PROPERTY NOT COLUMN NAME
    private Set<FlightBook> flightbookings = new HashSet<>();

    public UserEntity(String firstName, String lastName, String email, String password, List<Role> roles,
            Set<FlightBook> flightbookings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.flightbookings = flightbookings;
    }
}

