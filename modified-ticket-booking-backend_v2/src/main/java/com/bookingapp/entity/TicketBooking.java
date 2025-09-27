package com.bookingapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ticket_bookings")
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "total_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCost;

    @Column(name = "transaction_id", nullable = false)
    private Integer transactionId;

    @Column(name = "transaction_mode", nullable = false)
    private String transactionMode;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "booked_seat_ids", length = 1000)
    private String bookedSeatIds;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "ticket_ref_id")
    private Ticket ticket;

    // Constructors
    public TicketBooking() {}

    public TicketBooking(LocalDate bookingDate, BigDecimal totalCost, Integer transactionId,
                        String transactionMode, String transactionStatus) {
        this.bookingDate = bookingDate;
        this.totalCost = totalCost;
        this.transactionId = transactionId;
        this.transactionMode = transactionMode;
        this.transactionStatus = transactionStatus;
    }

    // Getters and Setters
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }

    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }

    public String getTransactionMode() { return transactionMode; }
    public void setTransactionMode(String transactionMode) { this.transactionMode = transactionMode; }

    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Show getShow() { return show; }
    public void setShow(Show show) { this.show = show; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }


    // Helper: Convert comma-separated string to List<Long>
    public List<Long> getSeatIdsList() {
        if (bookedSeatIds == null || bookedSeatIds.isEmpty()) return new ArrayList<>();
        String[] arr = bookedSeatIds.split(",");
        List<Long> result = new ArrayList<>();
        for (String s : arr) {
            try {
                result.add(Long.parseLong(s.trim()));
            } catch (Exception e) {
                // handle invalid number format
            }
        }
        return result;
    }

    // Helper: Set from List<Long>
    public void setSeatIdsList(List<Long> seatIds) {
        if (seatIds == null || seatIds.isEmpty()) {
            this.bookedSeatIds = "";
        } else {
            this.bookedSeatIds = seatIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
    }

    // Getter and Setter for bookedSeatIds
    public String getBookedSeatIds() {
        return bookedSeatIds;
    }

    public void setBookedSeatIds(String bookedSeatIds) {
        this.bookedSeatIds = bookedSeatIds;
    }


}