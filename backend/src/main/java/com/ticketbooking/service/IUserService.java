package com.ticketbooking.service;

import com.ticketbooking.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
    User addNewUser(User user);
    User signIn(User user);
    User signOut(User user);

    // ADD THIS:
    UserDetails loadUserByUsername(String email);
}
