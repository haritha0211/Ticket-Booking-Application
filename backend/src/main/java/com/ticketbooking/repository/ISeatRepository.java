package com.ticketbooking.repository;

import com.ticketbooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeatRepository extends JpaRepository<Seat, Integer> {

}
