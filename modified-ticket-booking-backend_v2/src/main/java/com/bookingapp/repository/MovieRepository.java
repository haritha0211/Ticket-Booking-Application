package com.bookingapp.repository;

import com.bookingapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByMovieGenre(String movieGenre);
    List<Movie> findByLanguage(String language);
    List<Movie> findByMovieNameContainingIgnoreCase(String movieName);
}