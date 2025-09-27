package com.bookingapp.service;

import com.bookingapp.dto.SeatDTO;
import com.bookingapp.entity.Seat;
import com.bookingapp.entity.Show;
import com.bookingapp.entity.ShowSeatBooking;
import com.bookingapp.entity.TicketBooking;
import com.bookingapp.exception.ResourceNotFoundException;
import com.bookingapp.repository.SeatRepository;
import com.bookingapp.repository.ShowRepository;
import com.bookingapp.repository.ShowSeatBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatBookingRepository showSeatBookingRepository;

    @Autowired
    private ShowRepository showRepository;

    public List<SeatDTO> getAllSeats() {
        return seatRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SeatDTO getSeatById(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found with id: " + seatId));
        return convertToDTO(seat);
    }

    public List<SeatDTO> getAvailableSeatsByScreen(Long screenId) {
        return seatRepository.findAllSeatsByScreenId(screenId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SeatDTO> getSeatsByScreen(Long screenId) {
        return seatRepository.findByScreenScreenId(screenId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // NEW: Get available seats for a specific show
    public List<SeatDTO> getAvailableSeatsByShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));

        List<Seat> availableSeats = seatRepository.findAvailableSeatsForShow(show.getScreen().getScreenId(), showId);
        return availableSeats.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // NEW: Get booked seats for a specific show
    public List<SeatDTO> getBookedSeatsByShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));

        List<Seat> bookedSeats = seatRepository.findBookedSeatsForShow(show.getScreen().getScreenId(), showId);
        return bookedSeats.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // NEW: Get seats by screen and show (with booking status per show)
    public List<SeatDTO> getSeatsByScreenAndShow(Long screenId, Long showId) {
        List<Seat> allSeats = seatRepository.findAllSeatsByScreenId(screenId);
        Set<Long> bookedSeatIds = Set.copyOf(showSeatBookingRepository.findUnavailableSeatIdsByShowId(showId));

        return allSeats.stream().map(seat -> {
            SeatDTO dto = convertToDTO(seat);
            // Set booking status based on show-specific booking
            dto.setIsBooked(bookedSeatIds.contains(seat.getSeatId()));
            dto.setIsBlocked(false); // You can implement blocking logic similarly if needed
            return dto;
        }).collect(Collectors.toList());
    }

    // Book seats for a specific show and booking
    public void bookSeatsForShow(List<Long> seatIds, Long showId, TicketBooking booking) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + showId));
        List<Seat> seats = seatRepository.findAllById(seatIds);

        // Check if seats are already booked for this show
        Set<Long> bookedSeatIds = Set.copyOf(showSeatBookingRepository.findUnavailableSeatIdsByShowId(showId));
        for (Long seatId : seatIds) {
            if (bookedSeatIds.contains(seatId)) {
                Seat seat = seats.stream().filter(s -> s.getSeatId().equals(seatId)).findFirst().orElse(null);
                String seatNum = seat != null ? seat.getSeatNumber() : "Unknown";
                throw new RuntimeException("Seat " + seatNum + " is already booked for this show");
            }
        }

        // Booking seats for this show
        for (Seat seat : seats) {
            ShowSeatBooking ssb = new ShowSeatBooking(show, seat, booking);
            showSeatBookingRepository.save(ssb);
        }
    }

    // Cancel seat bookings for a booking ID
    public void cancelSeatsForBooking(Long bookingId) {
        showSeatBookingRepository.deleteByBookingTicketId(bookingId);
    }

    private SeatDTO convertToDTO(Seat seat) {
        SeatDTO dto = new SeatDTO();
        dto.setSeatId(seat.getSeatId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setType(seat.getType());
        dto.setPrice(seat.getPrice());
        // Since seat booking is per show now, these global flags are no longer meaningful
        dto.setIsBooked(false);
        dto.setIsBlocked(false);
        if (seat.getScreen() != null) {
            dto.setScreenId(seat.getScreen().getScreenId());
        }
        return dto;
    }
}
