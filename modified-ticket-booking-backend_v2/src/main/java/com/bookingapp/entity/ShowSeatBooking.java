package com.bookingapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "show_seat_bookings", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"show_id", "seat_id"})
})
public class ShowSeatBooking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
    
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private TicketBooking booking;
    
    @Column(name = "is_booked", nullable = false)
    private Boolean isBooked = true;
    
    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked = false;
    
    // Constructors
    public ShowSeatBooking() {}
    
    public ShowSeatBooking(Show show, Seat seat, TicketBooking booking) {
        this.show = show;
        this.seat = seat;
        this.booking = booking;
        this.isBooked = true;
        this.isBlocked = false;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Show getShow() { return show; }
    public void setShow(Show show) { this.show = show; }
    
    public Seat getSeat() { return seat; }
    public void setSeat(Seat seat) { this.seat = seat; }
    
    public TicketBooking getBooking() { return booking; }
    public void setBooking(TicketBooking booking) { this.booking = booking; }
    
    public Boolean getIsBooked() { return isBooked; }
    public void setIsBooked(Boolean isBooked) { this.isBooked = isBooked; }
    
    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }
}