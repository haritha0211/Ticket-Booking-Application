package com.ticketbooking.service.impl;

import com.ticketbooking.model.TicketBooking;
import com.ticketbooking.repository.IBookingRepository;
import com.ticketbooking.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;

    @Override
    public TicketBooking addBooking(TicketBooking booking) {
        booking.setTransactionId(UUID.randomUUID().toString());
        booking.setBookingDate(LocalDate.now());
        return bookingRepository.save(booking);
    }

    @Override
    public TicketBooking updateBooking(TicketBooking booking) {
        TicketBooking existingBooking = bookingRepository.findById(booking.getTicketId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        existingBooking.setTransactionStatus(booking.getTransactionStatus());
        existingBooking.setTransactionMode(booking.getTransactionMode());
        existingBooking.setTotalCost(booking.getTotalCost());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public TicketBooking cancelBooking(TicketBooking booking) {
        booking.setTransactionStatus(TicketBooking.TransactionStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    @Override
    public List<TicketBooking> showAllBooking(Integer movieId) {
        return bookingRepository.findBookingsByMovieId(movieId);
    }

    @Override
    public List<TicketBooking> showAllBooking(LocalDate date) {
        return bookingRepository.findBookingsByDate(date);
    }

    @Override
    public List<TicketBooking> showBookingList(Integer showId) {
        return bookingRepository.findByShowShowId(showId);
    }

    @Override
    public Double calculateTotalCost(Integer bookingId) {
        return bookingRepository.calculateTotalCostByBookingId(bookingId);
    }
}
