package com.ticketbooking.controller;

import com.ticketbooking.dto.LoginRequest;
import com.ticketbooking.dto.LoginResponse;
import com.ticketbooking.dto.SignupRequest;
import com.ticketbooking.model.Customer;
import com.ticketbooking.service.ICustomerService;
import com.ticketbooking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ICustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        Customer customer = customerService.viewCustomer(loginRequest.getCustomerId());
        String token = jwtUtil.generateToken(customer.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, customer));
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> signup(@RequestBody @Valid SignupRequest signupRequest) {
        Customer customer = new Customer();
        customer.setCustomerName(signupRequest.getName());
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(signupRequest.getPassword());
        customer.setMobileNumber(signupRequest.getMobileNumber());
        customer.setAddress(signupRequest.getAddress());
        Customer savedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }
}
