package com.ticketbooking.service.impl;

import com.ticketbooking.model.Movie;
import com.ticketbooking.repository.IMovieRepository;
import com.ticketbooking.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final IMovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setMovieName(movie.getMovieName());
        existingMovie.setMovieGenre(movie.getMovieGenre());
        existingMovie.setMovieHours(movie.getMovieHours());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setDescription(movie.getDescription());

        return movieRepository.save(existingMovie);
    }

    @Override
    public Movie removeMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie viewMovie(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public List<Movie> viewMovieList() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> viewMovieList(Integer theatreId) {
        return movieRepository.findMoviesByTheatreId(theatreId);
    }

    @Override
    public List<Movie> viewMovieList(LocalDate date) {
        return movieRepository.findMoviesByShowDate(date);
    }
}
