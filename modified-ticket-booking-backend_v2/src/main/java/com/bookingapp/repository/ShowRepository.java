package com.bookingapp.repository;

import com.bookingapp.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieMovieId(Long movieId);
    List<Show> findByScreenScreenId(Long screenId);
    List<Show> findByTheatreTheatreId(Long theatreId);

    @Query("SELECT s FROM Show s WHERE s.showStartTime >= :startTime AND s.showEndTime <= :endTime")
    List<Show> findShowsBetweenTimes(@Param("startTime") LocalDateTime startTime, 
                                   @Param("endTime") LocalDateTime endTime);
}