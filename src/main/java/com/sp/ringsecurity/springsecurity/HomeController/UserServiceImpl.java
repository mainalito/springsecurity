package com.sp.ringsecurity.springsecurity.HomeController;

import com.sp.ringsecurity.springsecurity.models.Roles;
import com.sp.ringsecurity.springsecurity.models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserSErvice {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void save(UserRegistrationDto registrationDto) {

        UserPerson userPerson = new UserPerson();
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        
        userPerson = new UserPerson(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(), registrationDto.getUsername(),
                encodedPassword, Collections.singletonList(new Roles("ROLE_USER")));
        userRepository.save(userPerson);

    }

    // LOGIN FUNCTIONALITY
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserPerson userPerson = userRepository.findByUsername(username);
        if (userPerson == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new User(userPerson.getEmail(), userPerson.getPassword(), mapRolesToAuthorities(userPerson.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }
}
