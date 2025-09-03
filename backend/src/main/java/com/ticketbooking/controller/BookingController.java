package com.ticketbooking.controller;

import com.ticketbooking.model.TicketBooking;
import com.ticketbooking.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final IBookingService bookingService;

    @PostMapping("/")
    public ResponseEntity<TicketBooking> addBooking(@RequestBody TicketBooking booking) {
        return ResponseEntity.ok(bookingService.addBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketBooking> updateBooking(@PathVariable Integer id, @RequestBody TicketBooking booking) {
        booking.setTicketId(id);
        return ResponseEntity.ok(bookingService.updateBooking(booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TicketBooking> cancelBooking(@PathVariable Integer id) {
        TicketBooking booking = bookingService.showBookingList(id).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return ResponseEntity.ok(bookingService.cancelBooking(booking));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<TicketBooking>> getBookingsByMovie(@PathVariable Integer movieId) {
        return ResponseEntity.ok(bookingService.showAllBooking(movieId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<TicketBooking>> getBookingsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(bookingService.showAllBooking(localDate));
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<List<TicketBooking>> getBookingsByShow(@PathVariable Integer showId) {
        return ResponseEntity.ok(bookingService.showBookingList(showId));
    }

    @GetMapping("/cost/{bookingId}")
    public ResponseEntity<Double> getTotalCostForBooking(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.calculateTotalCost(bookingId));
    }
}
