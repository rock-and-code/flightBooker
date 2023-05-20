package com.ericlara.flightBooker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    protected AuthenticationManager authenticationManager;

    // METHOD TO RENDER THE REGISTRATION FORM TO THE USER AFTER PRESSING REGISTER BUTTON ON LOG IN FORM
    @GetMapping("/register")
    public String register(Model model) {
        // Add a new UserDto object to the model
        model.addAttribute("userDto", new UserDto());

        // Return the view name for the registration form
        return "authentication/register";
    }

    // METHOD TO PROCESS THE REGISTRATION FORM INFO AND AUTO LOG IN USER IF DATA IS CORRECT AND USER
    // IS NOT ALREADY REGISTERED BASED ON EMAIL PROVIDED
    @PostMapping("/register")
    public String userRegistration(final @Valid UserDto userDto, BindingResult result, Model model,
            HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // Validate the registration form data
        if (result.hasErrors()) {
            // Add the UserDto object to the model so the errors can be displayed
            model.addAttribute("userDto", userDto);

            // Redirect back to the registration form with an error message
            return "authentication/register";
        }

        // Try to register the user
        try {
            userService.register(userDto);
        } catch (UserAlreadyExistsException e) {
            // If the user already exists, add an error message to the registration form
            result.rejectValue("email", "userDto.email", "An account already exists with that email");
            model.addAttribute("userDto", userDto);

            // Redirect back to the registration form
            return "authentication/register";
        }

        // Auto-login the user after registration
        authWithAuthManager(request, userDto.getEmail(), userDto.getPassword());

        // Redirect to the home page with a success message
        return "redirect:/?success";
    }

    /**
     * Authenticates the user using the username and password provided.
     * This helper function is used to auto-login the user after successful registration.
     * @param request The HTTP request
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
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

    }

}
