package com.sp.ringsecurity.springsecurity.HomeController;

import java.util.regex.Pattern;

import com.sp.ringsecurity.springsecurity.models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public static boolean checkForRegex(String registrationNumber){
        Pattern pattern = Pattern.compile("^\\w{3}[- .]\\w{3}[- .]\\w{3}[/ .]\\w{4}$");
        return pattern.matcher(registrationNumber).matches();
    }
    @PostMapping
    public String registerUserAccount(  RedirectAttributes redirectAttributes,  @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult result) throws Exception {


        UserPerson userPerson =new UserPerson();
        String registrationNumber = userRegistrationDto.getUsername();
        if(checkForRegex(registrationNumber)){
            userPerson.setUsername(registrationNumber);
            userSErvice.save(userRegistrationDto);
            return "redirect:/registration?success";
        }
        redirectAttributes.addFlashAttribute("errorMessage","Provide correct details");
        return "redirect:/registration";
    }
}
