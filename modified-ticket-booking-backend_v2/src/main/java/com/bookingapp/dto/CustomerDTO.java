package com.bookingapp.dto;

public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String address;
    private String email;
    private String mobileNumber;
    private String password;

    // Constructors
    public CustomerDTO() {}

    public CustomerDTO(Long customerId, String customerName, String address, String email, String mobileNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    // Getters and Setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}