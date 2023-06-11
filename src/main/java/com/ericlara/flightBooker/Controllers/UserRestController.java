package com.ericlara.flightBooker.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Models.UserNotFoundException;
import com.ericlara.flightBooker.Models.UserXML;
import com.ericlara.flightBooker.Models.UsersXML;
import com.ericlara.flightBooker.Services.UserService;
import com.ericlara.flightBooker.Services.UserXMLService;
import com.ericlara.flightBooker.util.UserXMLUtil;

@RestController
public class UserRestController {

  // The base URL for all user-related endpoints
  public static final String USER_PATH = "/api/v1/users";

  // The URL for a specific flight endpoint
  public static final String USER_PATH_ID = USER_PATH + "/{userId}";
  
  @Autowired
  private UserXMLService userXMLService;
  
  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // METHOD TO GET ALL REGISTER USER-> FOR DEMOSTRATOIN PURPOSES ONLY AS WE ARE
  // LEAKING TOO MUCH INFORMATION TO USERS
  @GetMapping(value = USER_PATH, produces = MediaType.APPLICATION_XML_VALUE)
  public UsersXML getUsers() {
    UsersXML usersXML = new UsersXML();
    usersXML.setUsers(userXMLService.findAllUsers());
    return usersXML;
  }

  // Endpoint to register a user
  @PostMapping(value = USER_PATH, produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<UserXML> registerUser(@RequestBody UserXML user) throws UserAlreadyExistsException {

    // Register the user
    try {
      userXMLService.register(user);
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(409).header("X-Status-Reason", e.getMessage()).build();
    }

    try {
      String encryptedPass = passwordEncoder.encode(user.getPassword());
      user.setPassword(encryptedPass);
      UserXMLUtil.registerUser(user);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Get user
    UserEntity savedUser = userService.findUserByEmail(user.getEmail());

    // Set the Location header to the URL of the register user
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", "/api/v1/users/" + savedUser.getId());

    // Return a response with HTTP status code 201 (Created)
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  // Endpoint to get a user by ID
  @GetMapping(value = USER_PATH_ID, produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<UserXML> getUserById(@PathVariable("userId") Long id) {

    // Try to find the flight by ID
    try {
      return new ResponseEntity<>(userXMLService.findUserById(id), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      // If the flight is not found, return a response with HTTP status code 302
      // (Found)
      return ResponseEntity.notFound().build();
    }
  }

}
