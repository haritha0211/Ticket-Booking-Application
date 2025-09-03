package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "no_of_seats", nullable = false)
    private Integer noOfSeats;

    @Column(name = "ticket_status")
    private Boolean ticketStatus = true;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private TicketBooking booking;

    @ManyToMany
    @JoinTable(
        name = "ticket_seats",
        joinColumns = @JoinColumn(name = "ticket_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seatNumbers;
}
