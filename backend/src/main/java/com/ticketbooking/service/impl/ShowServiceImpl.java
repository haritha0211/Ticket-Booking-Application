package com.ticketbooking.service.impl;

import com.ticketbooking.model.Show;
import com.ticketbooking.repository.IShowRepository;
import com.ticketbooking.service.IShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements IShowService {

    private final IShowRepository showRepository;

    @Override
    public Show addShow(Show show) {
        return showRepository.save(show);
    }

    @Override
    public Show updateShow(Show show) {
        Show existingShow = showRepository.findById(show.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        existingShow.setShowName(show.getShowName());
        existingShow.setShowStartTime(show.getShowStartTime());
        existingShow.setShowEndTime(show.getShowEndTime());
        existingShow.setMovie(show.getMovie());
        existingShow.setScreen(show.getScreen());
        existingShow.setTheatre(show.getTheatre());

        return showRepository.save(existingShow);
    }

    @Override
    public Show removeShow(Show show) {
        showRepository.delete(show);
        return show;
    }

    @Override
    public Show viewShow(Show show) {
        return showRepository.findById(show.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));
    }

    @Override
    public List<Show> viewShowList(Integer theatreId) {
        return showRepository.findByTheatreTheatreId(theatreId);
    }

    @Override
    public List<Show> viewShowList(LocalDate date) {
        return showRepository.findShowsByDate(date);
    }

    @Override
    public List<Show> viewAllShows() {
        return showRepository.findAll();
    }
    @Override
public Show viewShow(Integer showId) {
    return showRepository.findById(showId)
            .orElseThrow(() -> new RuntimeException("Show not found"));
}

}
