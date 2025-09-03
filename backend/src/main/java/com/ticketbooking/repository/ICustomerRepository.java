package com.ticketbooking.repository;

import com.ticketbooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByMobileNumber(String mobileNumber);

    @Query("SELECT c FROM Customer c JOIN c.bookings b JOIN b.show s WHERE s.movie.movieId = :movieId")
    List<Customer> findCustomersByMovieId(Integer movieId);
}
