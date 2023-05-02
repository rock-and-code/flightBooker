package com.ericlara.flightBooker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ericlara.flightBooker.Models.User;
import com.ericlara.flightBooker.Models.UserDto;
import com.ericlara.flightBooker.Services.UserService;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Validated @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null,
                    "User already registered");
                    return "/registration";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registration";
        }
        userService.saveUser(userDto);
        return "redirect:/?success";
    }
}
