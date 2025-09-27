package com.bookingapp.dto;

import java.math.BigDecimal;

public class SeatDTO {
    private Long seatId;
    private String seatNumber;
    private String type;
    private BigDecimal price;
    private Boolean isBooked;
    private Boolean isBlocked;
    private Long screenId;

    // Constructors
    public SeatDTO() {}

    public SeatDTO(Long seatId, String seatNumber, String type, BigDecimal price, Boolean isBooked, Boolean isBlocked) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.type = type;
        this.price = price;
        this.isBooked = isBooked;
        this.isBlocked = isBlocked;
    }

    // Getters and Setters
    public Long getSeatId() { return seatId; }
    public void setSeatId(Long seatId) { this.seatId = seatId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Boolean getIsBooked() { return isBooked; }
    public void setIsBooked(Boolean isBooked) { this.isBooked = isBooked; }

    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }

    public Long getScreenId() { return screenId; }
    public void setScreenId(Long screenId) { this.screenId = screenId; }
}