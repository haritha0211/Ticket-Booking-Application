package com.bookingapp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theatre_id")
    private Long theatreId;

    @Column(name = "theatre_name", nullable = false)
    private String theatreName;

    @Column(name = "theatre_contact")
    private String theatreContact;

    @Column(name = "theatre_address")
    private String theatreAddress;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Screen> screens;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    private List<Show> shows;

    @ManyToMany
    @JoinTable(
        name = "theatre_movies",
        joinColumns = @JoinColumn(name = "theatre_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    // Constructors
    public Theatre() {}

    public Theatre(String theatreName, String theatreContact, String theatreAddress) {
        this.theatreName = theatreName;
        this.theatreContact = theatreContact;
        this.theatreAddress = theatreAddress;
    }

    // Getters and Setters
    public Long getTheatreId() { return theatreId; }
    public void setTheatreId(Long theatreId) { this.theatreId = theatreId; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }

    public String getTheatreContact() { return theatreContact; }
    public void setTheatreContact(String theatreContact) { this.theatreContact = theatreContact; }

    public String getTheatreAddress() { return theatreAddress; }
    public void setTheatreAddress(String theatreAddress) { this.theatreAddress = theatreAddress; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public List<Screen> getScreens() { return screens; }
    public void setScreens(List<Screen> screens) { this.screens = screens; }

    public List<Show> getShows() { return shows; }
    public void setShows(List<Show> shows) { this.shows = shows; }

    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}