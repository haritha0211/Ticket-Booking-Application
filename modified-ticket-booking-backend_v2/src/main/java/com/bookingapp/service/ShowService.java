package com.bookingapp.service;

import com.bookingapp.dto.ShowDTO;
import com.bookingapp.entity.Show;
import com.bookingapp.entity.Movie;
import com.bookingapp.entity.Screen;
import com.bookingapp.entity.Theatre;
import com.bookingapp.exception.ResourceNotFoundException;
import com.bookingapp.repository.ShowRepository;
import com.bookingapp.repository.MovieRepository;
import com.bookingapp.repository.ScreenRepository;
import com.bookingapp.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public List<ShowDTO> getAllShows() {
        return showRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShowDTO getShowById(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));
        return convertToDTO(show);
    }

    public List<ShowDTO> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieMovieId(movieId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShowDTO createShow(ShowDTO showDTO) {
        Show show = convertToEntity(showDTO);
        Show savedShow = showRepository.save(show);
        return convertToDTO(savedShow);
    }

    public void deleteShow(Long showId) {
        if (!showRepository.existsById(showId)) {
            throw new ResourceNotFoundException("Show not found with id: " + showId);
        }
        showRepository.deleteById(showId);
    }

    private ShowDTO convertToDTO(Show show) {
        ShowDTO dto = new ShowDTO();
        dto.setShowId(show.getShowId());
        dto.setShowName(show.getShowName());
        dto.setShowStartTime(show.getShowStartTime());
        dto.setShowEndTime(show.getShowEndTime());

        if (show.getMovie() != null) {
            dto.setMovieId(show.getMovie().getMovieId());
            dto.setMovieName(show.getMovie().getMovieName());
        }

        if (show.getScreen() != null) {
            dto.setScreenId(show.getScreen().getScreenId());
            dto.setScreenName(show.getScreen().getScreenName());
        }

        if (show.getTheatre() != null) {
            dto.setTheatreId(show.getTheatre().getTheatreId());
            dto.setTheatreName(show.getTheatre().getTheatreName());
        }

        return dto;
    }

    private Show convertToEntity(ShowDTO dto) {
        Show show = new Show();
        show.setShowName(dto.getShowName());
        show.setShowStartTime(dto.getShowStartTime());
        show.setShowEndTime(dto.getShowEndTime());

        if (dto.getMovieId() != null) {
            Movie movie = movieRepository.findById(dto.getMovieId())
                    .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
            show.setMovie(movie);
        }

        if (dto.getScreenId() != null) {
            Screen screen = screenRepository.findById(dto.getScreenId())
                    .orElseThrow(() -> new ResourceNotFoundException("Screen not found"));
            show.setScreen(screen);
        }

        if (dto.getTheatreId() != null) {
            Theatre theatre = theatreRepository.findById(dto.getTheatreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Theatre not found"));
            show.setTheatre(theatre);
        }

        return show;
    }
}