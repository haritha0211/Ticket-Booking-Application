package com.bookingapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Long showId;

    @Column(name = "show_name", nullable = false)
    private String showName;

    @Column(name = "show_start_time", nullable = false)
    private LocalDateTime showStartTime;

    @Column(name = "show_end_time", nullable = false)
    private LocalDateTime showEndTime;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<TicketBooking> bookings;

    // Constructors
    public Show() {}

    public Show(String showName, LocalDateTime showStartTime, LocalDateTime showEndTime) {
        this.showName = showName;
        this.showStartTime = showStartTime;
        this.showEndTime = showEndTime;
    }

    // Getters and Setters
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }

    public String getShowName() { return showName; }
    public void setShowName(String showName) { this.showName = showName; }

    public LocalDateTime getShowStartTime() { return showStartTime; }
    public void setShowStartTime(LocalDateTime showStartTime) { this.showStartTime = showStartTime; }

    public LocalDateTime getShowEndTime() { return showEndTime; }
    public void setShowEndTime(LocalDateTime showEndTime) { this.showEndTime = showEndTime; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Screen getScreen() { return screen; }
    public void setScreen(Screen screen) { this.screen = screen; }

    public Theatre getTheatre() { return theatre; }
    public void setTheatre(Theatre theatre) { this.theatre = theatre; }

    public List<TicketBooking> getBookings() { return bookings; }
    public void setBookings(List<TicketBooking> bookings) { this.bookings = bookings; }
}