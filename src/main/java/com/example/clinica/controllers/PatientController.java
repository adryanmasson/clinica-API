package com.example.clinica.controllers;

import com.example.clinica.dto.ApiResponse;
import com.example.clinica.dto.AppointmentDTO;
import com.example.clinica.dto.PatientHistoryDTO;
import com.example.clinica.models.Patient;
import com.example.clinica.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patient>>> listPatients() {
        List<Patient> patients = patientService.listPatients();
        String message = patients.isEmpty()
                ? "No patients found."
                : "Patients listed successfully.";
        ApiResponse<List<Patient>> body = ApiResponse.success(message, patients);

        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> findPatientById(@PathVariable Integer id) {
        Patient patient = patientService.findPatientById(id);
        ApiResponse<Patient> body = ApiResponse.success("Patient found successfully.", patient);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Patient>> createPatient(@RequestBody Patient patient) {
        Patient created = patientService.createPatient(patient);
        ApiResponse<Patient> body = ApiResponse.success("Patient created successfully.", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(
            @PathVariable Integer id,
            @RequestBody Patient updatedPatient) {
        Patient updated = patientService.updatePatient(id, updatedPatient);
        ApiResponse<Patient> body = ApiResponse.success("Patient updated successfully.", updated);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        ApiResponse<Void> body = ApiResponse.success("Patient deleted successfully.", null);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}/age")
    public ResponseEntity<ApiResponse<Integer>> calculatePatientAge(@PathVariable Integer id) {
        Integer age = patientService.calculatePatientAge(id);
        ApiResponse<Integer> body = ApiResponse.success("Age calculated successfully.", age);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<ApiResponse<List<PatientHistoryDTO>>> listPatientHistory(
            @PathVariable Integer id) {

        List<PatientHistoryDTO> history = patientService.listPatientHistory(id);

        String message = history.isEmpty()
                ? "No history found for this patient."
                : "Patient history returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, history));
    }

    @GetMapping("/{id}/report/appointments/{months}")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> appointmentsReportLastMonths(
            @PathVariable("id") Integer patientId,
            @PathVariable("months") Integer months) {

        List<AppointmentDTO> report = patientService.appointmentsReportLastMonths(patientId, months);

        String message = report.isEmpty()
                ? "No appointments found in the last " + months + " months."
                : "Appointment report for the last " + months + " months returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, report));
    }

    @GetMapping("/report/specialties")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> countPatientsBySpecialty() {
        List<Map<String, Object>> result = patientService.countPatientsBySpecialty();

        String message = result.isEmpty()
                ? "No specialties found."
                : "Patient count by specialty returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(message, result));
    }

}
