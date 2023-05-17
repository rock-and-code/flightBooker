package com.ericlara.flightBooker.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    // Render the login form
    @GetMapping
    public String login(Model model) {
        // Return the view name for the login form
        return "authentication/login";
    }
    
}
