package com.bookingapp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "description")
    private String description;

    @Column(name = "language")
    private String language;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "movie_hours")
    private Integer movieHours;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows;

    @ManyToMany(mappedBy = "movies")
    private List<Theatre> theatres;

    // Constructors
    public Movie() {}

    public Movie(String movieName, String description, String language, String movieGenre, Integer movieHours) {
        this.movieName = movieName;
        this.description = description;
        this.language = language;
        this.movieGenre = movieGenre;
        this.movieHours = movieHours;
    }

    // Getters and Setters
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getMovieGenre() { return movieGenre; }
    public void setMovieGenre(String movieGenre) { this.movieGenre = movieGenre; }

    public Integer getMovieHours() { return movieHours; }
    public void setMovieHours(Integer movieHours) { this.movieHours = movieHours; }

    public List<Show> getShows() { return shows; }
    public void setShows(List<Show> shows) { this.shows = shows; }

    public List<Theatre> getTheatres() { return theatres; }
    public void setTheatres(List<Theatre> theatres) { this.theatres = theatres; }
}