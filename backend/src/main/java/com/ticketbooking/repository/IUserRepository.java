package com.ticketbooking.repository;

import com.ticketbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(Integer userId);
    Optional<User> findByEmail(String email);  // <-- Add this line
    Optional<User> findByUserId(Long userId);

}
