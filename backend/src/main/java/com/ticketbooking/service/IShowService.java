package com.ticketbooking.service;

import com.ticketbooking.model.Show;
import java.time.LocalDate;
import java.util.List;

public interface IShowService {
    Show viewShow(Integer showId);
    Show addShow(Show show);
    Show updateShow(Show show);
    Show removeShow(Show show);
    Show viewShow(Show show);
    List<Show> viewShowList(Integer theatreId);
    List<Show> viewShowList(LocalDate date);
    List<Show> viewAllShows();
}
