package com.bookingapp.dto;

import java.time.LocalDateTime;

public class ShowDTO {
    private Long showId;
    private String showName;
    private LocalDateTime showStartTime;
    private LocalDateTime showEndTime;
    private Long movieId;
    private String movieName;
    private Long screenId;
    private String screenName;
    private Long theatreId;
    private String theatreName;

    // Constructors
    public ShowDTO() {}

    // Getters and Setters
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }

    public String getShowName() { return showName; }
    public void setShowName(String showName) { this.showName = showName; }

    public LocalDateTime getShowStartTime() { return showStartTime; }
    public void setShowStartTime(LocalDateTime showStartTime) { this.showStartTime = showStartTime; }

    public LocalDateTime getShowEndTime() { return showEndTime; }
    public void setShowEndTime(LocalDateTime showEndTime) { this.showEndTime = showEndTime; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public Long getScreenId() { return screenId; }
    public void setScreenId(Long screenId) { this.screenId = screenId; }

    public String getScreenName() { return screenName; }
    public void setScreenName(String screenName) { this.screenName = screenName; }

    public Long getTheatreId() { return theatreId; }
    public void setTheatreId(Long theatreId) { this.theatreId = theatreId; }

    public String getTheatreName() { return theatreName; }
    public void setTheatreName(String theatreName) { this.theatreName = theatreName; }
}