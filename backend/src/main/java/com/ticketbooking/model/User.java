package com.ticketbooking.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email; // Required for Spring Data repository

    @Column(nullable = false)
    private String password;

    @Column
    private String phone;
@Column
private String role;

public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}

    // Add additional fields as needed (e.g., roles, address, etc.)

    // Getter and Setter methods
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
