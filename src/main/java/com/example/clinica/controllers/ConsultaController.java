package com.example.clinica.controllers;

import com.example.clinica.dto.AgendarConsultaDTO;
import com.example.clinica.dto.ApiResponse;
import com.example.clinica.dto.AtualizarConsultaDTO;
import com.example.clinica.dto.ConsultaDTO;
import com.example.clinica.services.ConsultaService;

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
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConsultaDTO>>> listarConsultas() {
        List<ConsultaDTO> consultas = consultaService.listarConsultas();
        String mensagem = consultas.isEmpty() ? "No appointments found." : "Appointments listed successfully.";
        ApiResponse<List<ConsultaDTO>> body = ApiResponse.success(mensagem, consultas);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultaDTO>> buscarConsultaPorId(@PathVariable Integer id) {
        ConsultaDTO consulta = consultaService.buscarConsultaPorId(id);
        ApiResponse<ConsultaDTO> body = ApiResponse.success("Appointment found successfully.", consulta);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/agendar")
    public ResponseEntity<ApiResponse<ConsultaDTO>> agendarConsulta(@RequestBody AgendarConsultaDTO dto) {
        ConsultaDTO consulta = consultaService.agendarConsulta(dto.getIdPaciente(), dto.getIdMedico(),
                dto.getData(), dto.getHoraInicio(), dto.getHoraFim());
        return ResponseEntity.ok(ApiResponse.success("Appointment scheduled successfully", consulta));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultaDTO>> atualizar(
            @PathVariable Integer id,
            @RequestBody AtualizarConsultaDTO dto) {

        ConsultaDTO consultaAtualizada = consultaService.atualizarConsulta(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Appointment updated successfully", consultaAtualizada));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<ConsultaDTO>> cancelar(@PathVariable Integer id) {
        ConsultaDTO consultaCancelada = consultaService.cancelarConsulta(id);
        return ResponseEntity.ok(ApiResponse.success("Appointment cancelled successfully", consultaCancelada));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<ApiResponse<List<ConsultaDTO>>> listarPorPaciente(@PathVariable Integer id) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorPaciente(id);

        String mensagem = consultas.isEmpty()
                ? "No appointments found for the patient."
                : "Patient's appointments returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(mensagem, consultas));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<ApiResponse<List<ConsultaDTO>>> listarPorMedico(@PathVariable Integer id) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorMedico(id);

        String mensagem = consultas.isEmpty()
                ? "No appointments found for the doctor."
                : "Doctor's appointments returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(mensagem, consultas));
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<ApiResponse<List<ConsultaDTO>>> listarPorData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<ConsultaDTO> consultas = consultaService.listarConsultasPorData(data);

        String mensagem = consultas.isEmpty()
                ? "No appointments found for this date."
                : "Appointments for the date returned successfully.";

        return ResponseEntity.ok(ApiResponse.success(mensagem, consultas));
    }

}