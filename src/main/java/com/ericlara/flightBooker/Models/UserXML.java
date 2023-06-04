package com.ericlara.flightBooker.Models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@XmlRootElement(name = "user")
@JacksonXmlRootElement(localName = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Data
public class UserXML  implements Serializable {
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
    //@JsonIgnore //TO NOT DISPLAY THE PASSWORD ATTRIBUTE IN THE XML
    private String password;
}
