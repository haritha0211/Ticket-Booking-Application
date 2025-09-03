package com.ticketbooking.service;

import com.ticketbooking.model.TicketBooking;
import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    TicketBooking addBooking(TicketBooking booking);
    TicketBooking updateBooking(TicketBooking booking);
    TicketBooking cancelBooking(TicketBooking booking);
    List<TicketBooking> showAllBooking(Integer movieId);
    List<TicketBooking> showAllBooking(LocalDate date);
    List<TicketBooking> showBookingList(Integer showId);
    Double calculateTotalCost(Integer bookingId);
}
