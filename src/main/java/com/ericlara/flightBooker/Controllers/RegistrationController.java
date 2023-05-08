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

    //METHOD TO RENDER THE REGISTRATION FORM TO THE USER AFTER PRESING REGISTER BUTTON ON LOG IN FORM
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "authentication/register";
    }

    //METHOD TO PROCESS THE REGISTRATION FORM INFO AND AUTO LOG IN USER IF DATA IS CORRECT AND USER
    // IS NOT ALREADY REGISTERED BASED ON EMAL PROVIDED
    @PostMapping("/register")
    public String userRegistration(final @Valid UserDto userDto, BindingResult result, Model model,
    HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            // redirectAttributes.addFlashAttribute("message", "Registration Failed");
            // redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "authentication/register";
        }
        
        try {
            userService.register(userDto); //SAVE USER IN THE DBA
        } catch (UserAlreadyExistsException e) {
            result.rejectValue("email", "userDto.email", "An account alrady exists with that email");
            model.addAttribute("userDto", userDto);
            return "authentication/register";
        }

        //AUTO LOGIN USER AFTER REGISTRATION TO AVOID DEFALT SPRING BOOT BEHAVIOUR OF REQUIRING
        //LOGIN AFTER REGISTRATION
        authWithAuthManager(request, userDto.getEmail(), userDto.getPassword());
       
        return "redirect:/?success";
    }


   /**
    * Authenticates user Based on code posted on: https://coderanch.com/t/627731/frameworks/Autologin-site-registering-spring-security
    * This helper function is used to auto login user after success registration. To spring default behaviour
    * of requiring user to log in after registration. 
    * @param request
    * @param username
    * @param password
    */
    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //this step is important, otherwise the new login is not in session which is required by Spring Security
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    
}
