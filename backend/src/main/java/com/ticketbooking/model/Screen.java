package com.ticketbooking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "screen")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Integer screenId;

    // Avoid MySQL reserved keyword 'rows'
    @Column(name = "screen_rows", nullable = false)
    private Integer rows;

    @Column(name = "screen_columns", nullable = false)
    private Integer columns;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    // Constructors
    public Screen() {}

    public Screen(Integer rows, Integer columns, Theatre theatre) {
        this.rows = rows;
        this.columns = columns;
        this.theatre = theatre;
    }

    // Getters and setters
    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
