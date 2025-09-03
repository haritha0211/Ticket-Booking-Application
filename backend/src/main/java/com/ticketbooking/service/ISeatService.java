package com.ticketbooking.service;

import com.ticketbooking.model.Seat;

public interface ISeatService {
    Seat bookSeat(Seat seat);
    Seat cancelSeatBooking(Seat seat);
    Seat blockSeat(Seat seat);
}
