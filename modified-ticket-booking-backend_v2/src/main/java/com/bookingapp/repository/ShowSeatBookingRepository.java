package com.bookingapp.repository;

import com.bookingapp.entity.ShowSeatBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatBookingRepository extends JpaRepository<ShowSeatBooking, Long> {
    
    List<ShowSeatBooking> findByShowShowId(Long showId);
    
    List<ShowSeatBooking> findByShowShowIdAndIsBookedTrue(Long showId);
    
    List<ShowSeatBooking> findByShowShowIdAndIsBlockedTrue(Long showId);
    
    Optional<ShowSeatBooking> findByShowShowIdAndSeatSeatId(Long showId, Long seatId);
    
    @Query("SELECT ssb.seat.seatId FROM ShowSeatBooking ssb WHERE ssb.show.showId = :showId AND ssb.isBooked = true")
    List<Long> findBookedSeatIdsByShowId(@Param("showId") Long showId);
    
    @Query("SELECT ssb.seat.seatId FROM ShowSeatBooking ssb WHERE ssb.show.showId = :showId AND ssb.isBlocked = true")
    List<Long> findBlockedSeatIdsByShowId(@Param("showId") Long showId);
    
    @Query("SELECT ssb.seat.seatId FROM ShowSeatBooking ssb WHERE ssb.show.showId = :showId AND (ssb.isBooked = true OR ssb.isBlocked = true)")
    List<Long> findUnavailableSeatIdsByShowId(@Param("showId") Long showId);
    
    void deleteByBookingTicketId(Long ticketId);
}