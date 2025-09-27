package com.bookingapp.controller;

import com.bookingapp.dto.SeatDTO;
import com.bookingapp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        List<SeatDTO> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable Long id) {
        SeatDTO seat = seatService.getSeatById(id);
        return ResponseEntity.ok(seat);
    }

    @GetMapping("/available/{screenId}")
    public ResponseEntity<List<SeatDTO>> getAvailableSeatsByScreen(@PathVariable Long screenId) {
        List<SeatDTO> seats = seatService.getAvailableSeatsByScreen(screenId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<SeatDTO>> getSeatsByScreen(@PathVariable Long screenId) {
        List<SeatDTO> seats = seatService.getSeatsByScreen(screenId);
        return ResponseEntity.ok(seats);
    }

//    @PostMapping("/block")
//    public ResponseEntity<Void> blockSeats(@RequestBody List<Long> seatIds) {
//        seatService.blockSeats(seatIds);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/book")
//    public ResponseEntity<Void> bookSeats(@RequestBody List<Long> seatIds) {
//        seatService.bookSeats(seatIds);
//        return ResponseEntity.ok().build();
//    }

}