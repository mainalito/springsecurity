package com.sp.ringsecurity.springsecurity.HomeController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
