package com.ticketbooking.service.impl;

import com.ticketbooking.model.Seat;
import com.ticketbooking.repository.ISeatRepository;
import com.ticketbooking.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements ISeatService {

    private final ISeatRepository seatRepository;

    @Override
    public Seat bookSeat(Seat seat) {
        // Logic to book seat (mark as booked)
        return seatRepository.save(seat);
    }

    @Override
    public Seat cancelSeatBooking(Seat seat) {
        // Logic to cancel seat booking
        return seatRepository.save(seat);
    }

    @Override
    public Seat blockSeat(Seat seat) {
        // Logic to temporarily block seat during booking process
        return seatRepository.save(seat);
    }
}
