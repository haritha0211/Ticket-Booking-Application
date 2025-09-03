package com.ticketbooking.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private Integer customerId;
    private Integer showId;
    private List<Integer> seatIds; // IDs of seats being booked
    private String paymentMode;
}
