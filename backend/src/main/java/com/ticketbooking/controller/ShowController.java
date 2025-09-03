package com.ticketbooking.controller;

import com.ticketbooking.model.Show;
import com.ticketbooking.service.IShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ShowController {

    private final IShowService showService;

    @GetMapping("/")
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.viewAllShows());
    }

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Show>> getShowsByTheatre(@PathVariable Integer theatreId) {
        return ResponseEntity.ok(showService.viewShowList(theatreId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Show>> getShowsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(showService.viewShowList(localDate));
    }

    @PostMapping("/")
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        return ResponseEntity.ok(showService.addShow(show));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Integer id, @RequestBody Show show) {
        show.setShowId(id);
        return ResponseEntity.ok(showService.updateShow(show));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Show> removeShow(@PathVariable Integer id) {
        Show show = showService.viewShow(id);
        return ResponseEntity.ok(showService.removeShow(show));
    }
}
