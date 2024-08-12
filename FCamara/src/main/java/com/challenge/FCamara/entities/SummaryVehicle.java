package com.challenge.FCamara.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_summary_vehicles")
public class SummaryVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int entries;
    private int exits;

    public SummaryVehicle() {}

    public SummaryVehicle(Long id, LocalDateTime startTime, LocalDateTime endTime, int entries, int exits) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.entries = entries;
        this.exits = exits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getEntries() {
        return entries;
    }

    public void setEntries(int entries) {
        this.entries = entries;
    }

    public int getExits() {
        return exits;
    }

    public void setExits(int exits) {
        this.exits = exits;
    }
}
