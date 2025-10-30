package com.example.clinica.controllers;

import com.example.clinica.dto.AgendarConsultaDTO;
import com.example.clinica.dto.ApiResponse;
import com.example.clinica.dto.ConsultaDTO;
import com.example.clinica.models.Consulta;
import com.example.clinica.services.ConsultaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
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
        ApiResponse<List<ConsultaDTO>> body = ApiResponse.sucesso(
                consultas.isEmpty() ? "Nenhuma consulta encontrada." : "Consultas listadas com sucesso.",
                consultas);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultaDTO>> buscarConsultaPorId(@PathVariable Integer id) {
        ConsultaDTO consulta = consultaService.buscarConsultaPorId(id);
        ApiResponse<ConsultaDTO> body = ApiResponse.sucesso("Consulta encontrada com sucesso.", consulta);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/agendar")
    public ResponseEntity<ApiResponse<String>> agendarConsulta(@RequestBody AgendarConsultaDTO dto) {
        try {
            consultaService.agendarConsulta(dto.getIdPaciente(), dto.getIdMedico(),
                    dto.getData(), dto.getHoraInicio(), dto.getHoraFim());
            return ResponseEntity.ok(ApiResponse.sucesso("Consulta agendada com sucesso", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.erro(e.getMessage()));
        }
    }
}
