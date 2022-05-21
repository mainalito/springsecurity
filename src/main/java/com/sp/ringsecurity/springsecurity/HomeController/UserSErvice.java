package com.sp.ringsecurity.springsecurity.HomeController;

import com.sp.ringsecurity.springsecurity.models.UserPerson;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSErvice extends UserDetailsService {
    void save(UserRegistrationDto registrationDto);
}
