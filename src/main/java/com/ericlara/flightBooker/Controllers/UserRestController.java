package com.ericlara.flightBooker.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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

import jakarta.servlet.http.HttpServletRequest;

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
  protected AuthenticationManager authenticationManager;

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

  @PostMapping(value = USER_PATH + "/login", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<UserXML> authenticateUser(@RequestBody UserXML user, HttpServletRequest request) {

    // Get user
    UserEntity userEntity = userService.findUserByEmail(user.getEmail());

    if (userEntity != null) {

      // authenticate user
      authWithAuthManager(request, user.getEmail(), user.getPassword());

      // Set the X-Succesful-Login header to the user email 
      // For demostration purposes only
      HttpHeaders headers = new HttpHeaders();
      headers.add("X-Authenticated-User", user.getEmail());

      // Return a response with HTTP status code 200 (OK)
      return new ResponseEntity<>(headers, HttpStatus.OK);

    } else {
      // Return a response with HTTP status code 200 (OK)
      return ResponseEntity.notFound().build();
    }

  }

  /**
   * Authenticates the user using the username and password provided.
   * This helper function is used to auto-login the user after successful
   * registration.
   * 
   * @param request  The HTTP request
   * @param username The username
   * @param password The password
   */
  private void authWithAuthManager(HttpServletRequest request, String username, String password) {
    // Create a new UsernamePasswordAuthenticationToken object
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

    // Set the authentication details
    authToken.setDetails(new WebAuthenticationDetails(request));

    // Authenticate the user
    Authentication authentication = authenticationManager.authenticate(authToken);

    // Set the security context
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // Set the authentication in the session
    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
        SecurityContextHolder.getContext());

  }

}
