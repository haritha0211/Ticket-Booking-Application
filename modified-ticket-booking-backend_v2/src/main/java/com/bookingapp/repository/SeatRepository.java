package com.bookingapp.repository;

import com.bookingapp.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreenScreenId(Long screenId);
//    List<Seat> findByScreenScreenIdAndIsBookedFalse(Long screenId);
    List<Seat> findByType(String type);

//    @Query("SELECT s FROM Seat s WHERE s.screen.screenId = :screenId AND s.isBooked = false AND s.isBlocked = false")
//    List<Seat> findAvailableSeatsByScreenId(@Param("screenId") Long screenId);

    @Query("SELECT s FROM Seat s WHERE s.screen.screenId = :screenId ORDER BY s.seatNumber")
    List<Seat> findAllSeatsByScreenId(@Param("screenId") Long screenId);

    @Query("SELECT s FROM Seat s WHERE s.screen.screenId = :screenId " +
            "AND s.seatId NOT IN (SELECT ssb.seat.seatId FROM ShowSeatBooking ssb " +
            "WHERE ssb.show.showId = :showId AND (ssb.isBooked = true OR ssb.isBlocked = true)) " +
            "ORDER BY s.seatNumber")
    List<Seat> findAvailableSeatsForShow(@Param("screenId") Long screenId, @Param("showId") Long showId);

    @Query("SELECT s FROM Seat s WHERE s.screen.screenId = :screenId " +
            "AND s.seatId IN (SELECT ssb.seat.seatId FROM ShowSeatBooking ssb " +
            "WHERE ssb.show.showId = :showId AND ssb.isBooked = true) ORDER BY s.seatNumber")
    List<Seat> findBookedSeatsForShow(@Param("screenId") Long screenId, @Param("showId") Long showId);

    @Query("SELECT COUNT(s) FROM Seat s WHERE s.screen.screenId = :screenId")
    Long countSeatsByScreenId(@Param("screenId") Long screenId);


}