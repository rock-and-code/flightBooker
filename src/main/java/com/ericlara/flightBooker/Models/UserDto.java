package com.ericlara.flightBooker.Models;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//POJO TO HANDLES USER REGISTRATION FORM DATA

public class UserDto implements Serializable {

    @NotNull
    @NotEmpty(message = "User's first name cannot be empty.")
    private String firstName;

    @NotNull
    @NotEmpty(message = "User's last name cannot be empty")
    private String lastName;

    @NotNull
    @NotEmpty(message = "User's email cannot be empty")
    @Email(message = "Please enter a valid email (example@email.com)")
    private String email;

    @NotNull
    @NotEmpty(message = "User's password cannot be empty")
    private String password;

    public UserDto(){}

    public UserDto(@NotNull @NotEmpty(message = "User's first name cannot be empty.") String firstName,
            @NotNull @NotEmpty(message = "User's last name cannot be empty") String lastName,
            @NotNull @NotEmpty(message = "User's email cannot be empty") @Email(message = "Please enter a valid email (example@email.com)") String email,
            @NotNull @NotEmpty(message = "User's password cannot be empty") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
