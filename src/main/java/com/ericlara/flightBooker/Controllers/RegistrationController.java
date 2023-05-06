package com.ericlara.flightBooker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ericlara.flightBooker.Models.UserAlreadyExistsException;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Models.UserEntity;
import com.ericlara.flightBooker.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid UserDto userDto, BindingResult result, Model model,
    HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            // redirectAttributes.addFlashAttribute("message", "Registration Failed");
            // redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "register";
        }
        
        try {
            userService.register(userDto);
        } catch (UserAlreadyExistsException e) {
            result.rejectValue("email", "userDto.email", "An account alrady exists with that email");
            model.addAttribute("userDto", userDto);
            return "register";
        }

        //LOgin the user After success user registration
        UserEntity user = userService.findUserByEmail(userDto.getEmail());
        authenticateUserAndSetSession(user, request);

        return "redirect:/?success";
    }


    private void authenticateUserAndSetSession(UserEntity user, HttpServletRequest request) {
        String username = user.getEmail();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
