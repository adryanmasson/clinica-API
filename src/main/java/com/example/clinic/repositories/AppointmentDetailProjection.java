package com.example.clinic.repositories;

import java.sql.Date;
import java.sql.Time;

public interface AppointmentDetailProjection {
    Integer getId();

    Date getAppointmentDate();

    Time getStartTime();

    Time getEndTime();

    String getStatus();

    String getPatientName();

    String getDoctorName();
}
