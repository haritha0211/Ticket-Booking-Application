package com.ticketbooking.repository;
import org.springframework.stereotype.Repository;
import com.ticketbooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT DISTINCT m FROM Movie m JOIN m.theatres t WHERE t.theatreId = :theatreId")
    List<Movie> findMoviesByTheatreId(Integer theatreId);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.shows s WHERE DATE(s.showStartTime) = :date")
    List<Movie> findMoviesByShowDate(LocalDate date);
}
