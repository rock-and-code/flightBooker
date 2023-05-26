package com.ericlara.flightBooker.Models;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO TO HANDLES USER REGISTRATION FORM DATA

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
