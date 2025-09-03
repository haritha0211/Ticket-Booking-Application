package com.ticketbooking.repository;

import com.ticketbooking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface IShowRepository extends JpaRepository<Show, Integer> {
    @Query("SELECT s FROM Show s WHERE s.theatre.theatreId = :theatreId")
    List<Show> findByTheatreTheatreId(Integer theatreId);

    @Query("SELECT s FROM Show s WHERE DATE(s.showStartTime) = :date")
    List<Show> findShowsByDate(LocalDate date);
}
