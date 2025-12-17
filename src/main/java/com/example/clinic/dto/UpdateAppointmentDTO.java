package com.example.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateAppointmentDTO {
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status; // SCHEDULED, COMPLETED, CANCELLED

    // getters and setters
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
