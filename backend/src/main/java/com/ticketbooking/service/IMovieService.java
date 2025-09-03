package com.ticketbooking.service;

import com.ticketbooking.model.Movie;
import java.time.LocalDate;
import java.util.List;

public interface IMovieService {
    Movie addMovie(Movie movie);
    Movie updateMovie(Movie movie);
    Movie removeMovie(Integer movieId);
    Movie viewMovie(Integer movieId);
    List<Movie> viewMovieList();
    List<Movie> viewMovieList(Integer theatreId);
    List<Movie> viewMovieList(LocalDate date);
}
