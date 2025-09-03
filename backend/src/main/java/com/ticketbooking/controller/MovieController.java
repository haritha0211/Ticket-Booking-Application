package com.ticketbooking.controller;

import com.ticketbooking.model.Movie;
import com.ticketbooking.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    private final IMovieService movieService;

    @GetMapping("/")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.viewMovieList());
    }

    @PostMapping("/")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        movie.setMovieId(id);
        return ResponseEntity.ok(movieService.updateMovie(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> removeMovie(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.removeMovie(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.viewMovie(id));
    }
}
