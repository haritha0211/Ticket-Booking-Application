package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "theatre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Integer theatreId;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    @Column(name = "theatre_city", nullable = false)
    private String theatreCity;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_contact")
    private String managerContact;

    @ManyToMany
    @JoinTable(
        name = "theatre_movies",
        joinColumns = @JoinColumn(name = "theatre_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> listOfMovies;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> listOfScreens;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;
}
