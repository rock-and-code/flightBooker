package com.ericlara.flightBooker.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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
    private List<UserEntity> users = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }
    
}