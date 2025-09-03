package com.ticketbooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ticket_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "transaction_id", unique = true)
    private String transactionId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_mode", nullable = false)
    private TransactionMode transactionMode;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    private TransactionStatus transactionStatus;

    @Column(name = "total_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Ticket ticket;

    public enum TransactionMode {
        CREDIT_CARD, DEBIT_CARD, UPI, NET_BANKING
    }

    public enum TransactionStatus {
        PENDING, SUCCESS, FAILED, CANCELLED
    }
}
