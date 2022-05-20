package com.sp.ringsecurity.springsecurity.HomeController;

import com.sp.ringsecurity.springsecurity.models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserSErvice userSErvice;

    @GetMapping("/home")
    public String home() {
        return "This is home page";

    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
    
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        userSErvice.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
