package com.ticketbooking.service.impl;

import com.ticketbooking.model.User;
import com.ticketbooking.repository.IUserRepository;
import com.ticketbooking.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User signIn(User user) {
        return userRepository.findByUserId(user.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User signOut(User user) {
        // Implement logout logic if needed
        return user;
    }

    // --- Implementation for Spring Security Authentication ---
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User appUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(email);
        builder.password(appUser.getPassword());
        builder.roles(appUser.getRole()); // If role is String

        return builder.build();
    }
}
