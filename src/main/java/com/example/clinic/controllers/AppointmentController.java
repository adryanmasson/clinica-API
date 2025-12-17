package com.example.clinic.controllers;

import com.example.clinic.dto.ScheduleAppointmentDTO;
import com.example.clinic.dto.ApiResponse;
import com.example.clinic.dto.UpdateAppointmentDTO;
import com.example.clinic.dto.AppointmentDTO;
import com.example.clinic.services.AppointmentService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> listAppointments() {
        List<AppointmentDTO> appointments = appointmentService.listAppointments();
        String message = appointments.isEmpty() ? "No appointments found." : "Appointments listed successfully.";
        ApiResponse<List<AppointmentDTO>> body = ApiResponse.success(message, appointments);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentDTO>> findAppointmentById(@PathVariable Integer id) {
        AppointmentDTO appointment = appointmentService.findAppointmentById(id);
        ApiResponse<AppointmentDTO> body = ApiResponse.success("Appointment found successfully.", appointment);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/schedule")
    public ResponseEntity<ApiResponse<AppointmentDTO>> scheduleAppointment(@RequestBody ScheduleAppointmentDTO dto) {
        AppointmentDTO appointment = appointmentService.scheduleAppointment(dto.getPatientId(), dto.getDoctorId(),
                dto.getAppointmentDate(), dto.getStartTime(), dto.getEndTime());
        return ResponseEntity.ok(ApiResponse.success("Appointment scheduled successfully", appointment));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentDTO>> updateAppointment(
            @PathVariable Integer id,
            @RequestBody UpdateAppointmentDTO dto) {

        AppointmentDTO updated = appointmentService.updateAppointment(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Appointment updated successfully", updated));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<AppointmentDTO>> cancelAppointment(@PathVariable Integer id) {
        AppointmentDTO cancelled = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment cancelled successfully", cancelled));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> listByPatient(@PathVariable Integer id) {
        List<AppointmentDTO> appointments = appointmentService.listAppointmentsByPatient(id);

        String message = appointments.isEmpty()
                ? "No appointments found for the patient."
                : "Patient's appointments returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, appointments));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> listByDoctor(@PathVariable Integer id) {
        List<AppointmentDTO> appointments = appointmentService.listAppointmentsByDoctor(id);

        String message = appointments.isEmpty()
                ? "No appointments found for the doctor."
                : "Doctor's appointments returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, appointments));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> listByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AppointmentDTO> appointments = appointmentService.listAppointmentsByDate(date);

        String message = appointments.isEmpty()
                ? "No appointments found for this date."
                : "Appointments for the date returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, appointments));
    }

}