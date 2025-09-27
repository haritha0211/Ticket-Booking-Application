package com.bookingapp.repository;

import com.bookingapp.entity.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
    List<TicketBooking> findByCustomerCustomerId(Long customerId);
    List<TicketBooking> findByShowShowId(Long showId);
    List<TicketBooking> findByTransactionStatus(String transactionStatus);
}