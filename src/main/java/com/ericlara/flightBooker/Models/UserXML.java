package com.ericlara.flightBooker.Models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JacksonXmlRootElement(localName = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "<user>\n"
                + "\t<firstName>" + firstName + "</firstName>\n"
                + "\t<lastName>" + lastName + "</lastName>\n"
                + "\t<email>" + email + "</email>\n"
                + "\t<password>" + password + "</password>\n"
                + "</user>";
    }

    
}
