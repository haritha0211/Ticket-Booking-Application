package com.ticketbooking.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test") // base path for this controller
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from backend!";
    }

    @PostMapping("/echo")
    public String echo(@RequestBody String payload) {
        return "Received POST: " + payload;
    }
}
