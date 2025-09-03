package com.ticketbooking.repository;

import com.ticketbooking.model.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface IBookingRepository extends JpaRepository<TicketBooking, Integer> {
    @Query("SELECT tb FROM TicketBooking tb WHERE tb.show.movie.movieId = :movieId")
    List<TicketBooking> findBookingsByMovieId(Integer movieId);

    @Query("SELECT tb FROM TicketBooking tb WHERE tb.bookingDate = :date")
    List<TicketBooking> findBookingsByDate(LocalDate date);

    List<TicketBooking> findByShowShowId(Integer showId);

    @Query("SELECT SUM(tb.totalCost) FROM TicketBooking tb WHERE tb.ticketId = :bookingId")
    Double calculateTotalCostByBookingId(Integer bookingId);
}
