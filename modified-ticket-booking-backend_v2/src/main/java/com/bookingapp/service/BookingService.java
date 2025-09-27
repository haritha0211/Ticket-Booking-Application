package com.bookingapp.service;

import com.bookingapp.dto.BookingDTO;
import com.bookingapp.entity.*;
import com.bookingapp.exception.ResourceNotFoundException;
import com.bookingapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatBookingRepository showSeatBookingRepository;

    @Autowired
    private SeatService seatService;

    public List<BookingDTO> getAllBookings() {
        return ticketBookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long ticketId) {
        TicketBooking booking = ticketBookingRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + ticketId));
        return convertToDTO(booking);
    }

    public List<BookingDTO> getBookingsByCustomer(Long customerId) {
        return ticketBookingRepository.findByCustomerCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BigDecimal getBookingCost(Long ticketId) {
        TicketBooking booking = ticketBookingRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + ticketId));
        return booking.getTotalCost();
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate customer exists
        Customer customer = customerRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        // Validate show exists
        Show show = showRepository.findById(bookingDTO.getShowId())
                .orElseThrow(() -> new ResourceNotFoundException("Show not found"));

        // Validate and book seats
        if (bookingDTO.getSeatIds() != null && !bookingDTO.getSeatIds().isEmpty()) {
            List<Seat> seats = seatRepository.findAllById(bookingDTO.getSeatIds());

            if (seats.size() != bookingDTO.getSeatIds().size()) {
                throw new RuntimeException("One or more seats not found");
            }

            // Validate availability per show
            Set<Long> alreadyBookedSeats = Set.copyOf(
                    showSeatBookingRepository.findUnavailableSeatIdsByShowId(bookingDTO.getShowId())
            );

            for (Long seatId : bookingDTO.getSeatIds()) {
                if (alreadyBookedSeats.contains(seatId)) {
                    Seat seat = seats.stream()
                            .filter(s -> s.getSeatId().equals(seatId))
                            .findFirst()
                            .orElse(null);
                    String seatNumber = seat != null ? seat.getSeatNumber() : String.valueOf(seatId);
                    throw new RuntimeException("Seat " + seatNumber + " is already booked for this show");
                }
            }

            // Calculate total cost
            BigDecimal totalCost = seats.stream()
                    .map(Seat::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Create booking
            TicketBooking booking = new TicketBooking();
            booking.setBookingDate(LocalDate.now());
            booking.setTotalCost(totalCost);
            booking.setTransactionId(bookingDTO.getTransactionId());
            booking.setTransactionMode(bookingDTO.getTransactionMode());
            booking.setTransactionStatus("CONFIRMED");
            booking.setCustomer(customer);
            booking.setShow(show);
            booking.setSeatIdsList(bookingDTO.getSeatIds());

            TicketBooking savedBooking = ticketBookingRepository.save(booking);

            // Assign booked seats to this show and booking
            seatService.bookSeatsForShow(bookingDTO.getSeatIds(), bookingDTO.getShowId(), savedBooking);

            return convertToDTO(savedBooking);
        } else {
            throw new RuntimeException("No seats selected for booking");
        }
    }

    @Transactional
    public void deleteBooking(Long ticketId) {
        if (!ticketBookingRepository.existsById(ticketId)) {
            throw new ResourceNotFoundException("Booking not found with id: " + ticketId);
        }
        // Also cancel show seat bookings associated with this booking
        seatService.cancelSeatsForBooking(ticketId);
        ticketBookingRepository.deleteById(ticketId);
    }

    private BookingDTO convertToDTO(TicketBooking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setTicketId(booking.getTicketId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setTotalCost(booking.getTotalCost());
        dto.setTransactionId(booking.getTransactionId());
        dto.setTransactionMode(booking.getTransactionMode());
        dto.setTransactionStatus(booking.getTransactionStatus());

        if (booking.getCustomer() != null) {
            dto.setCustomerId(booking.getCustomer().getCustomerId());
            dto.setCustomerName(booking.getCustomer().getCustomerName());
        }

        if (booking.getShow() != null) {
            dto.setShowId(booking.getShow().getShowId());
            dto.setShowName(booking.getShow().getShowName());
        }

        // Using seat IDs stored in booking
        dto.setSeatIds(booking.getSeatIdsList());

        if (!dto.getSeatIds().isEmpty()) {
            List<Seat> seats = seatRepository.findAllById(dto.getSeatIds());
            dto.setSeatNumbers(
                    seats.stream().map(Seat::getSeatNumber).collect(Collectors.toList())
            );
        }

        return dto;
    }
}
