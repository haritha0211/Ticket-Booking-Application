package com.bookingapp.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_ref_id")
    private Long ticketRefId;

    @Column(name = "booking_ref", nullable = false)
    private String bookingRef;

    @Column(name = "no_of_seats", nullable = false)
    private Integer noOfSeats;

    @Column(name = "ticket_status", nullable = false)
    private Boolean ticketStatus = true;

    @ManyToMany
    @JoinTable(
        name = "ticket_seats",
        joinColumns = @JoinColumn(name = "ticket_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seatNumber;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketBooking> bookings;

    // Constructors
    public Ticket() {}

    public Ticket(String bookingRef, Integer noOfSeats) {
        this.bookingRef = bookingRef;
        this.noOfSeats = noOfSeats;
        this.ticketStatus = true;
    }

    // Getters and Setters
    public Long getTicketRefId() { return ticketRefId; }
    public void setTicketRefId(Long ticketRefId) { this.ticketRefId = ticketRefId; }

    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }

    public Integer getNoOfSeats() { return noOfSeats; }
    public void setNoOfSeats(Integer noOfSeats) { this.noOfSeats = noOfSeats; }

    public Boolean getTicketStatus() { return ticketStatus; }
    public void setTicketStatus(Boolean ticketStatus) { this.ticketStatus = ticketStatus; }

    public List<Seat> getSeatNumber() { return seatNumber; }
    public void setSeatNumber(List<Seat> seatNumber) { this.seatNumber = seatNumber; }

    public List<TicketBooking> getBookings() { return bookings; }
    public void setBookings(List<TicketBooking> bookings) { this.bookings = bookings; }
}