package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "movie_name", nullable = false)
    private String movieName;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "movie_hours")
    private String movieHours;

    private String language;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;

    @ManyToMany(mappedBy = "listOfMovies")
    private List<Theatre> theatres;
}
