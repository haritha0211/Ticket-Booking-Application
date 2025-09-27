package com.bookingapp.dto;

public class MovieDTO {
    private Long movieId;
    private String movieName;
    private String description;
    private String language;
    private String movieGenre;
    private Integer movieHours;

    // Constructors
    public MovieDTO() {}

    public MovieDTO(Long movieId, String movieName, String description, String language, String movieGenre, Integer movieHours) {
        this.movieId = movieId;
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
}