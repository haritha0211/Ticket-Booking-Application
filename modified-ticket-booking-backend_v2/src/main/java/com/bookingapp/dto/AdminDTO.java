package com.bookingapp.dto;

public class AdminDTO {
    private Long adminId;
    private String adminName;
    private String adminContact;
    private String email;
    private String password;

    // Constructors
    public AdminDTO() {}

    public AdminDTO(Long adminId, String adminName, String adminContact, String email) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminContact = adminContact;
        this.email = email;
    }

    // Getters and Setters
    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public String getAdminContact() { return adminContact; }
    public void setAdminContact(String adminContact) { this.adminContact = adminContact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}