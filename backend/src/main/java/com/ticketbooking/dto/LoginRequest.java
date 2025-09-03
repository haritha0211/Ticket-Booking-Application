package com.ticketbooking.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Integer customerId; // Optional if mapping customers by ID too
}
