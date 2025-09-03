package com.ticketbooking.dto;

import com.ticketbooking.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Customer customer;
}
