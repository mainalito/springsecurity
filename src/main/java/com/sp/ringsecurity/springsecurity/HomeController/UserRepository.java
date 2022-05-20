package com.sp.ringsecurity.springsecurity.HomeController;

import com.sp.ringsecurity.springsecurity.models.UserPerson;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserPerson, Long> {
    UserPerson findByUsername(String username);
}
