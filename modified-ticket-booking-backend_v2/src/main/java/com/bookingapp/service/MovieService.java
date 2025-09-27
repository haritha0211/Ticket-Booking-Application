package com.bookingapp.service;

import com.bookingapp.dto.MovieDTO;
import com.bookingapp.entity.Movie;
import com.bookingapp.exception.ResourceNotFoundException;
import com.bookingapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        return convertToDTO(movie);
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {
        Movie movie = convertToEntity(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return convertToDTO(savedMovie);
    }

    public MovieDTO updateMovie(Long movieId, MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        existingMovie.setMovieName(movieDTO.getMovieName());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setLanguage(movieDTO.getLanguage());
        existingMovie.setMovieGenre(movieDTO.getMovieGenre());
        existingMovie.setMovieHours(movieDTO.getMovieHours());

        Movie updatedMovie = movieRepository.save(existingMovie);
        return convertToDTO(updatedMovie);
    }

    public void deleteMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Movie not found with id: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setMovieId(movie.getMovieId());
        dto.setMovieName(movie.getMovieName());
        dto.setDescription(movie.getDescription());
        dto.setLanguage(movie.getLanguage());
        dto.setMovieGenre(movie.getMovieGenre());
        dto.setMovieHours(movie.getMovieHours());
        return dto;
    }

    private Movie convertToEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setMovieName(dto.getMovieName());
        movie.setDescription(dto.getDescription());
        movie.setLanguage(dto.getLanguage());
        movie.setMovieGenre(dto.getMovieGenre());
        movie.setMovieHours(dto.getMovieHours());
        return movie;
    }
}